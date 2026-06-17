package com.rajesh.commercehub.service;


public interface PaymentService {
	

	boolean processPayment(Long userId, double total);

}
