package com.mb.assignment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mb.assignment.entity.PaymentDetail;
import com.mb.assignment.entity.Product;
import com.mb.assignment.model.CheckoutPayment;
import com.mb.assignment.service.PaymentDetailService;
import com.mb.assignment.service.ProductService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;

import static com.mb.assignment.constants.UrlMapping.CHECKOUT_BASE_URL;
import static com.mb.assignment.constants.UrlMapping.PAYMENT;
import static com.mb.assignment.constants.UrlMapping.WEBHOOK;

@RestController
@RequestMapping(CHECKOUT_BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
public class CheckoutController {
	
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PaymentDetailService paymentDetailService;
	
	private static void init()
	{
		Stripe.apiKey = "sk_test_51LPhWbSBnwl8NuCHyBAi1kvgI7M49glQIin9F0taedeYJOOtQLrqSGZpOSvsLwWPksNn4djpX30yCsRI0E1NFPvb00TBmWl7Yx";
	}

	
	
private static Gson gson = new Gson();
	
	@PostMapping(PAYMENT)
	public String paymentWithCheckoutPage(@Valid @RequestBody CheckoutPayment payment) throws StripeException
	{
		
		init();
		
		Product product=productService.findById(payment.getProductId()).get();
		Long productPrice=product.getPrice();
	
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(payment.getSuccessUrl())
				.setCancelUrl(payment.getCancelUrl())
				.addLineItem(
						SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder()
												.setCurrency("usd")
												.setUnitAmount(productPrice*100)
												
												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
														.builder()
														.setName(payment.getName())
														.addImage("https://m.media-amazon.com/images/I/615ekapl+pL._SL1500_.jpg")
														.build())
												
												.build())
								.build())
				.build();
		
		Session session = Session.create(params);
		
		Map<String, String> responseData = new HashMap<>();
		
		responseData.put("id", session.getId());
		System.out.println(responseData.toString());
		return gson.toJson(responseData);
	}
	
	
	@PostMapping(WEBHOOK)
	private String saveSuccessData(HttpServletRequest request) {
		paymentDetailService.extractEventFromSignature(request);
		return "data added";
		
	}
	
}
