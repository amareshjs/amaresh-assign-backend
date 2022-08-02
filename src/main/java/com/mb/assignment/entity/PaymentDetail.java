package com.mb.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment_details")
public class PaymentDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name="customer")
	private String customerId;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="customer_name")
	private String customerName;
	
	
	@Column(name="customer_email")
	private String customerEmail;
	
	@Column(name="method")
	private String paymentMethod;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="status")
	private String status;
	
	@Column(name="country")
	private String country;
	
	@Column(name="payment_intent")
	private String paymentIntent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPaymentIntent() {
		return paymentIntent;
	}

	public void setPaymentIntent(String paymentIntent) {
		this.paymentIntent = paymentIntent;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public PaymentDetail() {
	}

	public PaymentDetail(Long id, String customerId, Long amount, String customerName, String customerEmail,
			String paymentMethod, String currency, String status, String country, String paymentIntent) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.amount = amount;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.paymentMethod = paymentMethod;
		this.currency = currency;
		this.status = status;
		this.country = country;
		this.paymentIntent = paymentIntent;
	}

	@Override
	public String toString() {
		return "PaymentDetail [id=" + id + ", customerId=" + customerId + ", amount=" + amount + ", customerName="
				+ customerName + ", customerEmail=" + customerEmail + ", paymentMethod=" + paymentMethod + ", currency="
				+ currency + ", status=" + status + ", country=" + country + ", paymentIntent=" + paymentIntent + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
