/**
 * 
 */
package com.zhihao.seckill.dao.cache;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.zhihao.seckill.pojo.Seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zzh
 * 2018年10月5日
 */
@Repository
@Component
public class RedisDao {
	
	private final Logger logger = LogManager.getLogger(RedisDao.class);

	private final JedisPool jedisPool;
	//protostuff序列化
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class); 
	
	public RedisDao(String ip, int port) {
		this.jedisPool = new JedisPool(ip, port);
	}
	
	//获取Seckill对象
	public Seckill getSeckill(long seckillId) {
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + seckillId;
				//protostuff序列化
				byte[] bytes = jedis.get(key.getBytes());
				if(bytes != null) {
					//创建空对象
					Seckill seckill = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					return seckill;
				}
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	//写入Seckil对象
	public String putSeckill(Seckill seckill) {
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:"+seckill.getSeckillId();
				//序列化对象
				byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
						LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				//超时时间
				int timeout = 60 * 60;
				String result = jedis.setex(key.getBytes(), timeout, bytes);
				return result;
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
