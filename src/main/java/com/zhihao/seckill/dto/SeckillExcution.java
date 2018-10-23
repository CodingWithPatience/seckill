/**
 * 
 */
package com.zhihao.seckill.dto;

import com.zhihao.seckill.enums.SeckillStateEnum;
import com.zhihao.seckill.pojo.SuccessKilled;

/**
 * 秒杀执行dto
 * @author zzh
 * 2018年9月28日
 */
public class SeckillExcution {

	// 执行结果状态标识
	private int state;
	
	// 秒杀商品id
	private long seckillId;
	
	// 状态说明
	private String stateInfo;
	
	// 秒杀成功返回对象
	private SuccessKilled successKilled;

	/**
	 * @param state
	 * @param seckillId
	 * @param stateInfo
	 * @param successKilled
	 */
	public SeckillExcution(long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
		super();
		this.state = stateEnum.getState();
		this.seckillId = seckillId;
		this.stateInfo = stateEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	/**
	 * @param seckillId2
	 * @param repeatKill
	 */
	public SeckillExcution(long seckillId, SeckillStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.seckillId = seckillId;
		this.stateInfo = stateEnum.getStateInfo();
	}

	public SeckillExcution(SeckillStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
}
