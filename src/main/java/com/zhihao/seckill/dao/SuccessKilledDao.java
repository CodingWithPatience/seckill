/**
 * 
 */
package com.zhihao.seckill.dao;

import java.util.List;

import com.zhihao.seckill.pojo.SuccessKilled;

/**
 * @author zzh
 * 2018年9月26日
 */
public interface SuccessKilledDao {
	
	List<SuccessKilled> list(int offset, int limit);
	
	SuccessKilled listById(long successKilledId);
	
	long listBySeckillId(long seckillId);
	
	int insert(long seckillId, long userPhone);
	
	int updateState(int state, long seckillId, long phone);

	SuccessKilled query(long seckillId, long userPhone);
}
