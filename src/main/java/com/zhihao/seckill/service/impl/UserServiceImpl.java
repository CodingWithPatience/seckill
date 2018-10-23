/**
 * 
 */
package com.zhihao.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihao.seckill.dao.UserDao;
import com.zhihao.seckill.pojo.User;
import com.zhihao.seckill.service.UserService;

/**
 * @author zzh
 * 2018年9月27日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	// 分页查询
	@Override
	public List<User> list(int offset, int limit) {
		return userDao.list(offset, limit);
	}

	// 根据用户id查询
	@Override
	public User listById(long userId) {
		return userDao.listById(userId);
	}

	// 验证登陆
	@Override
	public User get(User user) {
		return userDao.get(user);
	}

	// 用户名是否已经存在
	@Override
	public boolean isNameExist(String name) {
		return userDao.isNameExist(name);
	}

	// 邮箱是否已经存在
	@Override
	public boolean isEMailExist(String email) {
		return userDao.isEMailExist(email);
	}

	// 手机号是否已经存在
	@Override
	public boolean isPhoneExist(long phone) {
		return userDao.isPhoneExist(phone);
	}

	// 注册用户，插入成功，返回id
	@Override
	public long insert(User user) {
		long id = userDao.insert(user);
		return id;
	}
}
