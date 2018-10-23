/**
 * 
 */
package com.zhihao.seckill.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhihao.seckill.pojo.User;

/**
 * @author zzh
 * 2018年9月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@WebAppConfiguration
public class UserDaoTest {
	
	private static final Logger logger = LogManager.getLogger(UserDaoTest.class);

	@Autowired
	UserDao userDao;
	
	@Test
	public void listByIdTest() {
		User user = userDao.listById(1);
		logger.info("user info:"+user);
	}

	@Test
	public void listAllTest() {
		List<User> users = userDao.list(0, 5);
		logger.info("users info:"+users);
	}
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setName("hello");
		user.setPassword("zhang");
		user.setPhone(15636809874L);
		
		long id = userDao.insert(user);
		logger.info("id:"+id);
	}
}
