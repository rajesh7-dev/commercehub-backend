package com.rajesh.commercehub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {

	
	    private Long productId;
	    private String productName;
	    private double price;
	    private int quantity;
	    private double totalPrice;

}
