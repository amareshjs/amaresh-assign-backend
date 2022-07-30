package com.mb.assignment.enums;

public enum ErrorCode {
	
	INTERNAL_SERVER_ERROR(500),
	BAD_REQUEST(400),
	NOT_FOUND(404),
	UNAUTHORIZED(401),
	PRODUCT_ALREADY_EXISTS(601);
	
	private final int code;
	
	private ErrorCode(int code) {
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}

}
