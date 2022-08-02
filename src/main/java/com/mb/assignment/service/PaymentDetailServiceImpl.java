package com.mb.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mb.assignment.entity.PaymentDetail;
import com.mb.assignment.enums.ErrorCode;
import com.mb.assignment.exception.CustomException;
import com.mb.assignment.repository.PaymentDetailRepository;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService{

	@Autowired
	PaymentDetailRepository paymentDetailRepository;
	
	
	
	
	@Override
	public String save(PaymentDetail details) {
		try {
			paymentDetailRepository.save(details);
			return "Data Saved";
		}
		catch(Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}

}
