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
	@ResponseStatus(HttpStatus.ALREADY_REPORTED)
	public ErrorResponse handleCustomException(Exception e) {
		return new ErrorResponse(new Date(), "Something Went Wrong",
				e.getMessage(),HttpStatus.ALREADY_REPORTED.value(),
				e.getMessage());
	}

}
