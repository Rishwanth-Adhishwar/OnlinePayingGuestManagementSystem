package com.guestmanagement.model;

import java.time.LocalDate;

public class Payment {
	
	private static int idCounter = 1;
	
	private int paymentId;
	private int tenantId;
	private double amount;
	private LocalDate paymentDate;
	
	public Payment(int tenantId,double amount) {
		paymentId = idCounter++;
		this.tenantId = tenantId;
		this.amount = amount;
		paymentDate = LocalDate.now();
	}
	
	public int getPaymentId() {
		return paymentId;
	}
	
	public int getTenantId() {
		return tenantId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	
	@Override
	public String toString() {
	    return "Payment Details\n" +
	           "Payment ID : " + getPaymentId() + "\n" +
	           "Tenant ID  : " + getTenantId() + "\n" +
	           "Amount paid    : " + getAmount() + "\n" +
	           "Payment Date : " + getPaymentDate();
	          
	}

}
