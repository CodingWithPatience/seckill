/**
 * 
 */
package com.zhihao.seckill.dao;

import java.util.Date;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhihao.seckill.pojo.Seckill;

/**
 * @author zzh
 * 2018年9月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@WebAppConfiguration
public class SeckillDaoTest {

	@Autowired
	SeckillDao seckillDao;
	
	@Test
	public void listAllTest() {
		List<Seckill> seckills = seckillDao.list(2, 5);
		System.out.println(seckills);
	}

	@Test
	public void listByIdTest() {
		Seckill seckill = seckillDao.listById(1000);
		System.out.println(seckill);
	}
	
	@Test
	public void reduceNumberTest() throws ParseException {
		Date date = new Date();
		int result = seckillDao.reduceNumber(1000, date);
		System.out.println(result);
	}
}
