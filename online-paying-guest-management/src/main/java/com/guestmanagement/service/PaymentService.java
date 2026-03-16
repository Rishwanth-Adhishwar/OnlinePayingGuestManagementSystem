package com.guestmanagement.service;

import com.guestmanagement.model.Payment;

public class PaymentService {
	
	Payment[] payment = new Payment[100];
	
	int count = 0;
	
	public void makePayment(int tenantId,double amount) {
		
		if(count >= payment.length) {
			System.out.println("Payment storage full. Payment Id: "+payment[count-1].getPaymentId());
			return;
		}
		
		payment[count] = new Payment(tenantId,amount);
		
		count++;
		
		System.out.println("Payment Successfull.");
		
	}

}
