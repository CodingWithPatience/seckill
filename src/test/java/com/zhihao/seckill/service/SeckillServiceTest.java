/**
 * 
 */
package com.zhihao.seckill.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhihao.seckill.dto.Exposer;
import com.zhihao.seckill.dto.SeckillExcution;
import com.zhihao.seckill.pojo.Seckill;

/**
 * @author zzh
 * 2018年9月29日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@WebAppConfiguration
public class SeckillServiceTest {

	private static final Logger logger = LogManager.getLogger(UserServiceTest.class);

	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void listAllTest() {
		List<Seckill> seckills = seckillService.list(0, 5);
		logger.info("seckills info:"+seckills);
	}
	@Test
	public void listByIdTest() {
		Seckill seckill = seckillService.listById(1000);
		logger.info("seckill info:"+seckill);
	}
	@Test
	public void exposeUrlTest() {
		Exposer exposer = seckillService.exposeUrl(1000);
		logger.info("exposer info:"+exposer.getMd5());
	}
	@Test
	public void executeSeckillTest() {
		long phone = 13524689303L;
		String md5 = "67bed5228db8f7708c8e6660b4403536";
		SeckillExcution seckillExcution = seckillService.executeSeckill(1000, phone, md5);
		logger.info("seckills info:"+seckillExcution.getStateInfo());
	}
	@Test
	public void executeSeckillProcedureTest() {
		long id = 1002;
		long phone = 15527688933L;
		Exposer exposer = seckillService.exposeUrl(id);
		String md5 = exposer.getMd5();
		SeckillExcution seckillExcution = seckillService.executeSeckillProcedure(id, phone, md5);
		logger.info("seckillExcution state:"+seckillExcution.getState());
		logger.info("seckillExcution stateInfo:"+seckillExcution.getStateInfo());
	}
}
