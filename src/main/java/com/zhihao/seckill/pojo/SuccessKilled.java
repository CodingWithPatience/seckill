/**
 * 
 */
package com.zhihao.seckill.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author zzh
 * 2018年9月26日
 */
@Entity
@Table(name="success_killed")
public class SuccessKilled implements Serializable {

	private static final long serialVersionUID = 4577735542566397101L;
	private long seckillId;
	private long userPhone;
	private short state;
	private Date createTime;
	
	// 非数据库字段 ，一对多
	private List<Seckill> seckills;

	@Id
	@Column(name="seckill_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	@Column(name="user_phone")
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	@Column(name="state")
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Transient
	public List<Seckill> getSeckills() {
		return seckills;
	}
	public void setSeckills(List<Seckill> seckills) {
		this.seckills = seckills;
	}
	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", userPhone=" + userPhone
				+ ", state=" + state + ", createTime=" + createTime + ", seckills=" + seckills + "]";
	}
	
}
