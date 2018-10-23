/**
 * 
 */
package com.zhihao.seckill.service;

import java.util.List;

import com.zhihao.seckill.dto.Exposer;
import com.zhihao.seckill.dto.SeckillExcution;
import com.zhihao.seckill.exception.RepeatedSeckillException;
import com.zhihao.seckill.exception.SeckillClosedException;
import com.zhihao.seckill.exception.SeckillException;
import com.zhihao.seckill.pojo.Seckill;

/**
 * @author zzh
 * 2018年9月27日
 */
public interface SeckillService {
	static final int LIMIT = 10;

	List<Seckill> list(int offset, int limit);

	Seckill listById(long seckillId);
	
	Exposer exposeUrl(long seckillId);
	
	SeckillExcution executeSeckill(long seckillId, long phone, String md5)
		throws RepeatedSeckillException, SeckillClosedException, SeckillException;

	SeckillExcution executeSeckillProcedure(long seckillId, long phone, String md5);
}
