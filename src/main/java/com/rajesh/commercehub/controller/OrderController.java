package com.rajesh.commercehub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.commercehub.dto.Orderresponse;
import com.rajesh.commercehub.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	
	 
	private final OrderService orderService;
	
	@PostMapping("/checkout")
	public String checkout(@RequestParam Long userId) {
		
		orderService.placeOrder(userId);
		
		return "Order placed Successfully";
	}
	
	
	@GetMapping
	public List<Orderresponse> getOrders(@RequestParam Long userId){
		
		return orderService.getUserOrders(userId);
	}

}
