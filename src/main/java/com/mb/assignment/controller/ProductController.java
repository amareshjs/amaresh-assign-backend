package com.mb.assignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mb.assignment.entity.Product;
import com.mb.assignment.model.CheckoutPayment;
import com.mb.assignment.service.ProductService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping()
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping()
	public String addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	private static void init()
	{
		Stripe.apiKey = "sk_test_51LPhWbSBnwl8NuCHyBAi1kvgI7M49glQIin9F0taedeYJOOtQLrqSGZpOSvsLwWPksNn4djpX30yCsRI0E1NFPvb00TBmWl7Yx";
	}
	
	
	private static Gson gson = new Gson();
	@PostMapping("/payments")
	public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException
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
														.setName(payment.getName()).build())
												.build())
								.build())
				.build();
		
		Session session = Session.create(params);
		
		Map<String, String> responseData = new HashMap<>();
		
		responseData.put("id", session.getId());
		System.out.println(responseData.toString());
		return gson.toJson(responseData);
	}

}
