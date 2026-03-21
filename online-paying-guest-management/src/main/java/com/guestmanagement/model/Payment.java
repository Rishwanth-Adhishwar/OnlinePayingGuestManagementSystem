package com.guestmanagement.model;

import java.time.LocalDate;

class InvalidPaymentException extends RuntimeException {// Identify only at Runtime 
    public InvalidPaymentException(String message) {
        super(message);
    }
}

public class Payment {
	
	private static int idCounter = 1;
	
	private int paymentId;
	private int tenantId;
	private double amount;
	private LocalDate paymentDate;
	
	public Payment(int tenantId,double amount) {
		
		validateIds(tenantId, amount);
		
		paymentId = idCounter++;
		this.tenantId = tenantId;
		this.amount = amount;
		paymentDate = LocalDate.now();
	}
	
	private static void validateIds(int tenantId, double amount) {
        if (tenantId <= 0) {
            throw new InvalidPaymentException("Tenant ID must be a positive integer. Received: " + tenantId);
        }
        if (amount <= 0) {
            throw new InvalidPaymentException("Amount must be positive. Received: " + amount);
        }
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
	           "Payment ID  : " + getPaymentId() + "\n" +
	           "Tenant ID   : " + getTenantId() + "\n" +
	           "Amount paid : ₹" + String.format("%.2f", getAmount())+ "\n" +
	           "Payment Date: " + getPaymentDate();
	          
	}

}
