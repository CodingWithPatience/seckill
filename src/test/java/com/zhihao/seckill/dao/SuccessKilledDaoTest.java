/**
 * 
 */
package com.zhihao.seckill.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhihao.seckill.pojo.SuccessKilled;

/**
 * @author zzh
 * 2018年9月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@WebAppConfiguration
public class SuccessKilledDaoTest {

	@Autowired
	SuccessKilledDao successKilledDao;
	
	@Test
	public void listAllTest() {
		List<SuccessKilled> successKilleds = successKilledDao.list(0, 5);
		System.out.println(successKilleds);
	}
	
	@Test
	public void listByIdTest() {
		SuccessKilled successKilled = successKilledDao.listById(1);
		System.out.println(successKilled);
	}
	
	@Test
	public void listBySeckillIdTest() {
		long count = successKilledDao.listBySeckillId(1);
		System.out.println(count);
	}
	
	@Test
	public void insertTest() {
		int count = successKilledDao.insert(15, 2345656343L);
		System.out.println(count);
	}
}
