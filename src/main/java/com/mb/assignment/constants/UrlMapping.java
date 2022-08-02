package com.mb.assignment.constants;

public class UrlMapping
{
	private UrlMapping()
	{
		throw new IllegalStateException("Constants class.can't instatiate");
	}
	public static final String BASE_URL = "/api/v1/";
	public static final String PRODUCT_BASE_URL = BASE_URL+"products";
	public static final String CHECKOUT_BASE_URL = BASE_URL+"checkout";
	public static final String WEBHOOK = "/webhook";
	public static final String PAYMENT = "/payments";
	
}