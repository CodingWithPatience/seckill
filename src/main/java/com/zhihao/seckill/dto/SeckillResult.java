/**
 * 
 */
package com.zhihao.seckill.dto;

/**
 * 封装json结果，返回给前端
 * @author zzh
 * 2018年9月30日
 */
public class SeckillResult<T> {

	private boolean success;
	
	private T data;
	
	private String errMsg;
	
	/**
	 * @param success
	 * @param data
	 */
	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	/**
	 * @param success
	 * @param errMsg
	 */
	public SeckillResult(boolean success, String errMsg) {
		super();
		this.success = success;
		this.errMsg = errMsg;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
