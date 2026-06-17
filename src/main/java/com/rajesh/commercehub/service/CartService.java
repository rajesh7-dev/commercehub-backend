package com.rajesh.commercehub.service;


import com.rajesh.commercehub.dto.CartResponse;

public interface CartService {

	void addToCart(Long userId, Long productId, int quantity);

	void updateQuantity(Long userId, Long productId, int quantity);

	void removeItem(Long userId, Long productId);

	CartResponse getCartItems(Long userId);

	
}
