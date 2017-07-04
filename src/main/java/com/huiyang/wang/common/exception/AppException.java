package com.huiyang.wang.common.exception;

/**
 * 常规异常
 */
@SuppressWarnings("serial")
public class AppException extends Exception {
	private String code;

	public AppException(String code) {
		this.code = code;
	}

	public AppException(String code, String s) {
		super(s);
		this.code = code;
	}

	public AppException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public AppException(String code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
