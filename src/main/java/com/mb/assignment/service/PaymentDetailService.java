package com.mb.assignment.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.mb.assignment.entity.PaymentDetail;
import com.stripe.model.Event;

public interface PaymentDetailService {
	
	
	public String save(PaymentDetail details);	
	
	public ResponseEntity<?> extractEventFromSignature(HttpServletRequest request);
	
	public String saveSuccessData(Event event);
	

}
