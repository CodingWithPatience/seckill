/**
 * 
 */
package com.zhihao.seckill.service;

import java.util.List;

import com.zhihao.seckill.pojo.SuccessKilled;

/**
 * @author zzh
 * 2018年9月27日
 */
public interface SuccessKilledService {
	final static int FAIL = -1;
	final static int SUCCESS = 0;
	final static int PAYED = 1;
	final static int DELIVERIED = 2;

	// 分页查询
	List<SuccessKilled> list(int offset, int limit);
	
	// 根据id查询
	SuccessKilled listById(long successKilledId);
	
	// 根据秒杀产品id查询该产品成功秒杀的数量
	long listBySeckillId(long seckillId);
	
	// 秒杀成功，向数据库中插入数据
	void insert(long seckillId, long phone);
	
	// 更新状态 -1：无效、0：成功、1：已付款、2：已发货
	int updateState(int state, long seckillId, long phone);
}
