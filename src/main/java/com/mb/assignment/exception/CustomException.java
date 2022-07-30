package com.mb.assignment.exception;

import com.mb.assignment.enums.ErrorCode;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 6277873105663264707L;
	private ErrorCode errorCode;
	private String errorMsg;
	
	
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public CustomException() {
		
	}
	public CustomException(ErrorCode errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	
	
	
	
	
	

}
