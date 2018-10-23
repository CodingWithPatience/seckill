/**
 * 
 */
package com.zhihao.seckill.dao.cache;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhihao.seckill.dao.SeckillDao;
import com.zhihao.seckill.pojo.Seckill;

/**
 * @author zzh
 * 2018年10月5日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@WebAppConfiguration
public class RedisDaoTest {
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SeckillDao seckillDao;
	
	private long id = 1001;
	
	private final Logger logger = LogManager.getLogger(RedisDaoTest.class);

	/**
	 * Test method for {@link com.zhihao.seckill.dao.cache.RedisDao#getSeckill(long)}.
	 */
	@Test
	public void testGetSeckill() {
		Seckill seckill = redisDao.getSeckill(id);
		logger.info("seckill from redisDao:"+seckill);
		if(seckill==null) {
			Seckill sec = seckillDao.listById(id);
			logger.info("seckill from seckillDao:"+sec);
			String result = redisDao.putSeckill(sec);
			logger.info("putSeckill result:"+result);
			Seckill seck = redisDao.getSeckill(id);
			logger.info("seckill from redisDao:"+seck);
		}
	}

}
