package com.mb.assignment.exception;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 6277873105663264707L;
	private long errorCode;
	private String errorMsg;
	
	
	public long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(long errorCode) {
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
	
	public CustomException(long errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	
	
	
	
	

}
