/**
 * 
 */
package com.zhihao.seckill.exception;

/**
 * @author zzh
 * 2018年9月27日
 */
public class SeckillClosedException extends SeckillException {


	private static final long serialVersionUID = 2191108485953088438L;

	/**
	 * @param message
	 * @param cause
	 */
	public SeckillClosedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SeckillClosedException(String message) {
		super(message);
	}

}
