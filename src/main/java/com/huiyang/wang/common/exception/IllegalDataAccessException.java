package com.huiyang.wang.common.exception;

/**
 * 非法数据访问
 * 
 * @author KelvinZ
 * 
 */
@SuppressWarnings("serial")
public class IllegalDataAccessException extends AppException {

	public IllegalDataAccessException(String code) {
		super(code);
	}

	public IllegalDataAccessException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public IllegalDataAccessException(String code, String s) {
		super(code, s);
	}

	public IllegalDataAccessException(String code, Throwable cause) {
		super(code, cause);
	}

}
