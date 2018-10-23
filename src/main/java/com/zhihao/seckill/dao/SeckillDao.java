/**
 * 
 */
package com.zhihao.seckill.dao;

import java.util.Date;
import java.util.List;

import com.zhihao.seckill.pojo.Seckill;

/**
 * @author zzh
 * 2018年9月26日
 */
public interface SeckillDao {
	List<Seckill> list(int offset, int limit); 
	
	Seckill listById(long seckillId);
	
	int reduceNumber(long seckillId, Date killTime);
	
	int killByProcedure(long seckillId, long phone, Date killTime);
}
