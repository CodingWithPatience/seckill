/**
 * 
 */
package com.zhihao.seckill.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.zhihao.seckill.dao.SeckillDao;
import com.zhihao.seckill.dao.SuccessKilledDao;
import com.zhihao.seckill.dao.cache.RedisDao;
import com.zhihao.seckill.dto.Exposer;
import com.zhihao.seckill.dto.SeckillExcution;
import com.zhihao.seckill.enums.SeckillStateEnum;
import com.zhihao.seckill.exception.RepeatedSeckillException;
import com.zhihao.seckill.exception.SeckillClosedException;
import com.zhihao.seckill.exception.SeckillException;
import com.zhihao.seckill.pojo.Seckill;
import com.zhihao.seckill.pojo.SuccessKilled;
import com.zhihao.seckill.service.SeckillService;
import com.zhihao.seckill.service.SuccessKilledService;

/**
 * @author zzh
 * 2018年9月27日
 */
@Service
public class SeckillServiceImpl implements SeckillService {
	
	private static final Logger logger = LogManager.getLogger(SeckillServiceImpl.class);
	
	@Resource
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	@Autowired
	private RedisDao redisDao;
	
	// md5盐值字符串
	private final String slat = "oiawtoOIU@#$HIUOhwejnO*&%093*fl";

	// 分页查询
	@Override
	public List<Seckill> list(int offset, int limit) {
		return seckillDao.list(offset, limit);
	}

	// id查询
	@Override
	public Seckill listById(long seckillId) {
		return seckillDao.listById(seckillId);
	}

	// 秒杀操作
	@Override
	@Transactional(rollbackFor=RuntimeException.class)
	public SeckillExcution executeSeckill(long seckillId, long phone, String md5)
			throws RepeatedSeckillException, SeckillClosedException, SeckillException {
		
		if(md5 == null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		
		try {
			int insertCount = successKilledDao.insert(seckillId, phone); 
			if(insertCount <= 0) {
				// 重复秒杀
				throw new RepeatedSeckillException("seckill repeated");
			}
			else {
				// 秒杀成功，更新状态
				successKilledDao.updateState(SuccessKilledService.SUCCESS, seckillId, phone);
				
				Date now = new Date();
				// 减库存，使用行级锁，存在竞争
				int updateCount = seckillDao.reduceNumber(seckillId, now);
				
				if(updateCount <= 0) {
					// 秒杀结束
					throw new SeckillClosedException("seckill is closed");
				}
				else {
					SuccessKilled successKilled = successKilledDao.query(seckillId, phone);
					// 返回数据传输对象
					SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
					return seckillExcution;
				}
			}
		} catch (SeckillClosedException e1) {
			throw e1;
		} catch (RepeatedSeckillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 编译器异常转换为运行期异常
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}
	}

	// 暴露秒杀接口
	@Override
	public Exposer exposeUrl(long seckillId) {
		//redis缓存中获取对象
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(seckill == null) {
			//从MySQL数据库获取数据
			seckill = seckillDao.listById(seckillId);
			if(seckill == null) {
				Exposer exposer = new Exposer(false, seckillId);
				return exposer;
			}
			else {
				//把对象放入到redis缓存中
				redisDao.putSeckill(seckill);
			}
		}
		
		Date nowTime = new Date();
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		
		//秒杀未开始或秒杀已经结束
		if(nowTime.getTime()<=startTime.getTime()
				|| nowTime.getTime()>=endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(),
					startTime.getTime(), endTime.getTime());
		}
		
		String md5 = getMd5(seckillId);
		return new Exposer(true, md5, seckillId);
	}
	
	// md5加密
	private String getMd5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	// 使用MySQL存储过程执行秒杀操作
	@Override
	public SeckillExcution executeSeckillProcedure(long seckillId, long phone, String md5) {
		if(md5 == null || !md5.equals(getMd5(seckillId))) {
			return new SeckillExcution(seckillId, SeckillStateEnum.DATA_REWRITE);
		}
		
		Date nowTime = new Date();
		try {
			int result = seckillDao.killByProcedure(seckillId, phone, nowTime);
			if(result == 1) {
				return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS);
			}
			else {
				System.out.println("result:"+result);
				return new SeckillExcution(seckillId, SeckillStateEnum.stateOf(result));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new SeckillExcution(seckillId, SeckillStateEnum.INNER_ERROR);
		}
		
	}
}
