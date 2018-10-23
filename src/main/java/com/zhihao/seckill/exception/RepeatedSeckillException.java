/**
 * 
 */
package com.zhihao.seckill.exception;

/**
 * @author zzh
 * 2018年9月27日
 */
public class RepeatedSeckillException extends SeckillException {

	private static final long serialVersionUID = 3059771220114385770L;

	/**
	 * @param message
	 */
	public RepeatedSeckillException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RepeatedSeckillException(String message, Throwable cause) {
		super(message, cause);
	}

}
