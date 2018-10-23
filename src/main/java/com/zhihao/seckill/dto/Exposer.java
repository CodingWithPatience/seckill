/**
 * 
 */
package com.zhihao.seckill.dto;

/**
 * 秒杀暴露dto
 * @author zzh
 * 2018年9月28日
 */
public class Exposer {

	// 秒杀地址是否已经暴露
	private boolean exposed;
	
	// md5加密
	private String md5;

	// 秒杀商品id
	private long seckillId;
	
	// 系统当前时间（毛秒）
	private long nowTime;
	
	// 秒杀开始时间
	private long startTime;

	// 秒杀结束时间
	private long endTaime;

	/**
	 * @param exposed
	 * @param md5
	 * @param seckillId
	 */
	public Exposer(boolean exposed, String md5, long seckillId) {
		super();
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	/**
	 * @param exposed
	 * @param seckillId
	 */
	public Exposer(boolean exposed, long seckillId) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	/**
	 * @param exposed
	 * @param now
	 * @param startTime
	 * @param endTaime
	 */
	public Exposer(boolean exposed, long seckillId, long nowTime, long startTime, long endTaime) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.nowTime = nowTime;
		this.startTime = startTime;
		this.endTaime = endTaime;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getNowTime() {
		return nowTime;
	}

	public void setNowTime(long nowTime) {
		this.nowTime = nowTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTaime() {
		return endTaime;
	}

	public void setEndTaime(long endTaime) {
		this.endTaime = endTaime;
	}

	@Override
	public String toString() {
		return "Exposer [exposed=" + exposed + ", md5=" + md5 + ", seckillId=" + seckillId + ", nowTime=" + nowTime
				+ ", startTime=" + startTime + ", endTaime=" + endTaime + "]";
	}
	
	
}
