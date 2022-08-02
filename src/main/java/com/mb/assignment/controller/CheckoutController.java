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


	String webhookSecret="whsec_c14a33304da1d2afeea649f165742b5153bea51c5fad7fbaa5c87f350c9df1a9";
	
	
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
	private ResponseEntity<?> extractEventFromSignature(HttpServletRequest request, PaymentDetail paymentDetail)
	{
		String sigHeader = request.getHeader("Stripe-Signature");
		Event event = null;
		try
		{
			String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			event = Webhook.constructEvent(payload, sigHeader,webhookSecret);
		}
		catch (Exception e)
		{
			
		}
		if ("charge.succeeded".equals(event.getType()))
		{
			System.out.println("In Checkout Session completed");
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
			paymentDetailService.save(details);
		}
		return new ResponseEntity<>(event, HttpStatus.OK);
	}

}
