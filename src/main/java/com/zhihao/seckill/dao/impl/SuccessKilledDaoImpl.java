/**
 * 
 */
package com.zhihao.seckill.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zhihao.seckill.dao.SuccessKilledDao;
import com.zhihao.seckill.pojo.SuccessKilled;

/**
 * @author zzh
 * 2018年9月26日
 */
@Repository	
@Transactional
public class SuccessKilledDaoImpl implements SuccessKilledDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SuccessKilled> list(int offset, int limit) {
		Criteria criteria = getSession().createCriteria(SuccessKilled.class);
		return (List<SuccessKilled>) criteria.setFirstResult(offset).setMaxResults(limit).list();
	}
	
	@Override
	public SuccessKilled listById(long successKilledId) {
		SuccessKilled successKilled = (SuccessKilled) getSession().get(SuccessKilled.class, successKilledId);
		return successKilled;
	}
	
	@Override
	public long listBySeckillId(long seckillId) {
		Criteria criteria = getSession().createCriteria(SuccessKilled.class);
		return (Long) criteria.add(Restrictions.eq("seckillId", seckillId))
				.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	public int insert(long seckillId, long userPhone) {
		String sql = "insert ignore into success_killed(seckill_id, user_phone)"
				+ "values(?, ?)";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		sqlQuery.setLong(0, seckillId);
		sqlQuery.setLong(1, userPhone);
		int count = sqlQuery.executeUpdate();
		return count;
	}

	
	@Override
	public SuccessKilled query(long seckillId, long userPhone) {
		Criteria criteria = getSession().createCriteria(SuccessKilled.class);
		SuccessKilled successKilled = (SuccessKilled) criteria.add(
				Restrictions.eq("seckillId", seckillId)).add(Restrictions.eq("userPhone", userPhone)).uniqueResult();
		return successKilled;
	}

	@Override
	public int updateState(int state, long seckillId, long phone) {
		String hql = "update SuccessKilled as sk set sk.state=?"
				+ "where sk.seckillId=? and sk.userPhone=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, state);
		query.setLong(1, seckillId);
		query.setLong(2, phone);
		int updateCount = query.executeUpdate();
		return updateCount;
	}
}
