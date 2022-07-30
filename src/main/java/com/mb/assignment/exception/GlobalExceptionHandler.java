package com.mb.assignment.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mb.assignment.util.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value= {CustomException.class})
	public ErrorResponse handleCustomException(CustomException e) {
		return new ErrorResponse(new Date(), "Something Went Wrong",
				e.getMessage(),e.getErrorCode().getCode(),
				e.getMessage());
	}
}
