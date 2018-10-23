/**
 * 
 */
package com.zhihao.seckill.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zhihao.seckill.dao.SeckillDao;
import com.zhihao.seckill.pojo.Seckill;

/**
 * @author zzh
 * 2018年9月26日
 */
@Repository
@Transactional
public class SeckillDaoImpl implements SeckillDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private EntityManager entityManager;
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Seckill> list(int offset, int limit) {
		Criteria criteria = getSession().createCriteria(Seckill.class);
		return (List<Seckill>) criteria.setFirstResult(offset).setMaxResults(limit).list();
	}
	
	@Override
	public Seckill listById(long seckillId) {
		Seckill seckill = (Seckill) getSession().get(Seckill.class, seckillId);
		return seckill;
	}
	
	@Override
	public int reduceNumber(long seckillId, Date killTime) {
		String hql = "update Seckill as sk set sk.number = sk.number-1"
				+ " where sk.seckillId=? and sk.startTime<=? and sk.endTime>=? and sk.number>0";
		Query query = getSession().createQuery(hql);
		query.setLong(0, seckillId);
		query.setTimestamp(1, killTime);
		query.setTimestamp(2, killTime);
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public int killByProcedure(long seckillId, long phone, Date killTime) {
		// 通过EntityManager获取Session
		Session session = entityManager.unwrap(Session.class);
		
		// 创建存储过程调用语句
		ProcedureCall call = session.createStoredProcedureCall("excute_seckill");
		// 设置输入参数
		call.registerParameter("v_seckill_id", Long.class, ParameterMode.IN ).bindValue(seckillId);
		call.registerParameter("v_phone", Long.class, ParameterMode.IN ).bindValue(phone);
		call.registerParameter("v_kill_time", Date.class, ParameterMode.IN ).bindValue(killTime);
		// 设置输出参数
		call.registerParameter("r_result", Integer.class, ParameterMode.OUT );
		
		// 获取结果
		int result = (Integer) call.getOutputs().getOutputParameterValue("r_result");
		return result;
	}
}
