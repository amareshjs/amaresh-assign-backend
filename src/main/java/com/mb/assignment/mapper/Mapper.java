package com.mb.assignment.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mb.assignment.enums.ErrorCode;
import com.mb.assignment.exception.CustomException;

@Component
public class Mapper {

	public <T> T convert(Object srcObj, Class<T> targetClass) {
		T response = null;

		try {
			response = new ModelMapper().map(srcObj, targetClass);
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR,"Error while converting object"+e.getMessage());
		}
		return response;
	}

}