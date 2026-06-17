package com.rajesh.commercehub.service.impl;

import org.springframework.stereotype.Service;

import com.rajesh.commercehub.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Override
	public boolean processPayment(Long userId, double amount) {
		
		System.out.println("Payment Success for User" + userId);
		
		
		return true;  // Always Success
		
	}

}
