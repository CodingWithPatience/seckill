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

import com.zhihao.seckill.pojo.User;

/**
 * @author zzh
 * 2018年9月29日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@WebAppConfiguration
public class UserServiceTest {
	
	private static final Logger logger = LogManager.getLogger(UserServiceTest.class);

	@Autowired
	UserService userService;
	
	@Test
	public void listAllTest() {
		List<User> users = userService.list(0, 5);
		logger.info("users info:"+users);
	}
	
	@Test
	public void listByIdTest() {
		User user = userService.listById(1);
		logger.info("user info:"+user);
	}

	@Test
	public void isNameExistTest() {
		boolean exist = userService.isNameExist("zhang");
		logger.info("Is the name exist:"+exist);
	}
	@Test
	public void isPhoneExistTest() {
		boolean exist = userService.isPhoneExist(14345234634L);
		logger.info("Is the phone exist:"+exist);
	}
	@Test
	public void isEMailExistTest() {
		boolean exist = userService.isEMailExist("214228934@sdf.com");
		logger.info("Is the email exist:"+exist);
	}

}
