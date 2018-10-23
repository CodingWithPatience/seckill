/**
 * 
 */
package com.zhihao.seckill.dao;

import java.util.List;

import com.zhihao.seckill.pojo.User;

/**
 * @author zzh
 * 2018年9月26日
 */
public interface UserDao {

	List<User> list(int offset, int limit);
	
	User listById(long userId);
	
	User get(User user);
	
	boolean isNameExist(String name);

	boolean isEMailExist(String email);

	boolean isPhoneExist(long phone);

	long insert(User user);
}
