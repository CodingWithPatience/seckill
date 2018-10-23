/**
 * 
 */
package com.zhihao.seckill.exception;

/**
 * @author zzh
 * 2018年9月27日
 */
public class SeckillException extends RuntimeException {

	private static final long serialVersionUID = -7408298758436913089L;

	/**
	 * @param message
	 * @param cause
	 */
	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SeckillException(String message) {
		super(message);
	}
}
