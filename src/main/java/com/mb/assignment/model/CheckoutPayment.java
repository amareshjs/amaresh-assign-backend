package com.mb.assignment.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CheckoutPayment {
	
	@NotBlank(message="Name Cannot be Null")
	private String name;
		
	@NotNull(message = "Product Id Required")
	private Long productId;
	
	@NotBlank(message="Provide Success URL")
	private String successUrl;
	
	@NotBlank(message="Provide Cancel URL")
	private String cancelUrl;
	
	@NotNull(message = "Quantity Required")
	private long quantity;
	

	public String getName() {
		return name;
	}
	
	public Long getProductId() {
		return productId;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public long getQuantity() {
		return quantity;
	}

	
	
}