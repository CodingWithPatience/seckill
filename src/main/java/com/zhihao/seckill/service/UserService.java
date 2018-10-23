/**
 * 
 */
package com.zhihao.seckill.service;

import java.util.List;

import com.zhihao.seckill.pojo.User;

/**
 * @author zzh
 * 2018年9月27日
 */
public interface UserService {

	List<User> list(int offset, int limit);
	
	User listById(long userId);
	
	User get(User user);
	
	boolean isNameExist(String name);

	boolean isEMailExist(String mail);

	boolean isPhoneExist(long phone);

	long insert(User user);
}
