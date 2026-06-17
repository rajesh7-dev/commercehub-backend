package com.rajesh.commercehub.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
	
	
	private List<CartItemResponse> items;
	
	private double totalAmount;
	

}
