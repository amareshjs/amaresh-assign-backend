package com.mb.assignment.service;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mb.assignment.entity.PaymentDetail;
import com.mb.assignment.enums.ErrorCode;
import com.mb.assignment.exception.CustomException;
import com.mb.assignment.repository.PaymentDetailRepository;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService{

	@Autowired
	PaymentDetailRepository paymentDetailRepository;
	
	
	String webhookSecret="whsec_c14a33304da1d2afeea649f165742b5153bea51c5fad7fbaa5c87f350c9df1a9";
	
	
	
	
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
	
	@Override
	public ResponseEntity<?> extractEventFromSignature(HttpServletRequest request){
		String sigHeader = request.getHeader("Stripe-Signature");
		Event event = null;
		try
		{
			String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			event = Webhook.constructEvent(payload, sigHeader,webhookSecret);
		}
		catch(Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
		}
		if ("charge.succeeded".equals(event.getType()))
		{
			saveSuccessData(event);
		}
		return new ResponseEntity<>(event, HttpStatus.OK);
	}
	
	@Override
	public String saveSuccessData(Event event) {
		PaymentDetail details = new PaymentDetail();
		EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
		StripeObject stripeObject = null;
		if (dataObjectDeserializer.getObject().isPresent())
		{
			stripeObject = dataObjectDeserializer.getObject().get();
		}
		Charge charge = (Charge) stripeObject;
		System.out.println("Strip Object" + stripeObject);
		System.out.println(charge.getAmount());
		details.setCustomerEmail(charge.getBillingDetails().getEmail());
		details.setCustomerName(charge.getBillingDetails().getName());
		details.setCustomerId(charge.getCustomer());
		details.setAmount(charge.getAmount());
		details.setCurrency(charge.getCurrency());
		details.setCountry(charge.getBillingDetails().getAddress().getCountry());
		details.setPaymentIntent(charge.getPaymentIntent());
		details.setPaymentMethod(charge.getPaymentMethodDetails().getType());
		details.setStatus(charge.getStatus());
		paymentDetailRepository.save(details);
		
		return "data Added";
		
		
	}
	
	
	
		
	}
	
	
	
