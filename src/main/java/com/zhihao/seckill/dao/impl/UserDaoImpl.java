/**
 * 
 */
package com.zhihao.seckill.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zhihao.seckill.dao.UserDao;
import com.zhihao.seckill.pojo.User;

/**
 * @author zzh
 * 2018年9月26日
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> list(int offset, int limit) {
		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>) criteria.setFirstResult(offset).setFetchSize(limit).list();
				
	}
	
	@Override
	public User listById(long userId) {
		User user = (User) getSession().get(User.class, userId);
		return user;
	}
	
	@Override
	public User get(User user) {
		String name = user.getName();
		String password = user.getPassword();
		Criteria criteria = getSession().createCriteria(User.class);
		User authUser = (User) criteria.add(Restrictions.eq("name", name))
				.add(Restrictions.eq("password", password)).uniqueResult();
		return authUser;
	}
	
	@Override
	public boolean isNameExist(String name) {
		Criteria criteria = getSession().createCriteria(User.class);
		User authUser = (User) criteria.add(Restrictions.eq("name", name)).uniqueResult();
		if(authUser == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isEMailExist(String email) {
		Criteria criteria = getSession().createCriteria(User.class);
		User authUser = (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
		if(authUser == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isPhoneExist(long phone) {
		Criteria criteria = getSession().createCriteria(User.class);
		User authUser = (User) criteria.add(Restrictions.eq("phone", phone)).uniqueResult();
		if(authUser == null)
			return false;
		else
			return true;
	}

	@Override
	public long insert(User user) {
		Session session = getSession();
		long id = (long) session.save(user);
		return id;
	}
}
