/**
 * 
 */
package com.zhihao.seckill.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhihao.seckill.pojo.SuccessKilled;

/**
 * @author zzh
 * 2018年9月29日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@WebAppConfiguration
public class SuccessKilledServiceTest {
	private static final Logger logger = LogManager.getLogger(SuccessKilledServiceTest.class);
	
	@Autowired
	private SuccessKilledService successKilledService;

	/**
	 * @param
	 * @return
	 * 2018年9月29日
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.zhihao.seckill.service.SuccessKilledService#list(int, int)}.
	 */
	@Test
	public void testList() {
		List<SuccessKilled> successKilleds = successKilledService.list(0, 5);
		logger.info("succssKilleds info:"+successKilleds);
	}

	/**
	 * Test method for {@link com.zhihao.seckill.service.SuccessKilledService#listById(long)}.
	 */
	@Test
	public void testListById() {
		SuccessKilled successKilled = successKilledService.listById(10);
		logger.info("succssKilled info:"+successKilled);
	}

	/**
	 * Test method for {@link com.zhihao.seckill.service.SuccessKilledService#listBySeckillId(long)}.
	 */
	@Test
	public void testListBySeckillId() {
		long count = successKilledService.listBySeckillId(1000);
		logger.info("count info:"+count);
	}

	/**
	 * Test method for {@link com.zhihao.seckill.service.SuccessKilledService#insert(long, long)}.
	 */
	@Test
	public void testInsert() {
		
	}

	/**
	 * Test method for {@link com.zhihao.seckill.service.SuccessKilledService#updateState(int, long, long)}.
	 */
	@Test
	public void testUpdateState() {
		
	}

}
