package com.rajesh.commercehub.service;

import java.util.List;

import com.rajesh.commercehub.dto.Orderresponse;

public interface OrderService {


	void placeOrder(Long userId);

	List<Orderresponse> getUserOrders(Long userId);

}
