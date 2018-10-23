/**
 * 
 */
package com.zhihao.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihao.seckill.dao.SuccessKilledDao;
import com.zhihao.seckill.pojo.SuccessKilled;
import com.zhihao.seckill.service.SuccessKilledService;

/**
 * @author zzh
 * 2018年9月27日
 */
@Service
public class SuccessKilledServiceImpl implements SuccessKilledService {

	@Autowired
	SuccessKilledDao successKilledDao;

	// 分页查询
	@Override
	public List<SuccessKilled> list(int offset, int limit) {
		return successKilledDao.list(offset, limit);
	}

	// id查询
	@Override
	public SuccessKilled listById(long successKilledId) {
		return successKilledDao.listById(successKilledId);
	}

	// 根据秒杀商品id查询该商品成功秒杀的数量
	@Override
	public long listBySeckillId(long seckillId) {
		return successKilledDao.listBySeckillId(seckillId);
	}

	// 秒杀成功，向数据库中插入数据
	@Override
	public void insert(long seckillId, long phone) {
		successKilledDao.insert(seckillId, phone);
	}

	// 更新状态 -1：无效、0：成功、1：已付款、2：已发货
	@Override
	public int updateState(int state, long seckillId, long phone) {
		int updateCount = successKilledDao.updateState(state, seckillId, phone);
		return updateCount;
	}
}
