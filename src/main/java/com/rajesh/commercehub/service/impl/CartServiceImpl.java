package com.rajesh.commercehub.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rajesh.commercehub.dto.CartItemResponse;
import com.rajesh.commercehub.dto.CartResponse;
import com.rajesh.commercehub.entity.Cart;
import com.rajesh.commercehub.entity.CartItem;
import com.rajesh.commercehub.repository.CartItemRepository;
import com.rajesh.commercehub.repository.CartRepository;
import com.rajesh.commercehub.repository.ProductRepository;
import com.rajesh.commercehub.repository.UserRepository;
import com.rajesh.commercehub.service.CartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
	
	
	private final CartRepository cartRepository;
	
	private final CartItemRepository cartItemRepository;
	
	private final UserRepository userRepository;
	
	private final ProductRepository productRepository;
	
	
	//Adding product to cart
	@Override
	public void addToCart(Long userId, Long productId, int quantity) {

	    Cart cart = cartRepository.findByUserId(userId)
	            .orElseGet(() -> {
	                Cart newCart = new Cart();
	                newCart.setUser(userRepository.findById(userId).get());
	                return cartRepository.save(newCart);
	            });

	    Optional<CartItem> existingItem =
	            cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

	    if (existingItem.isPresent()) {
	        CartItem item = existingItem.get();
	        item.setQuantity(item.getQuantity() + quantity);
	        cartItemRepository.save(item);
	    } else {
	        CartItem item = new CartItem();
	        item.setCart(cart);
	        item.setProduct(productRepository.findById(productId).get());
	        item.setQuantity(quantity);
	        cartItemRepository.save(item);
	    }
	}
	
	//updating quantity
	@Override
	public void updateQuantity(Long userId, Long productId, int quantity) {

	    Cart cart = cartRepository.findByUserId(userId).get();

	    CartItem item = cartItemRepository
	            .findByCartIdAndProductId(cart.getId(), productId)
	            .orElseThrow(() -> new RuntimeException("Item not found"));

	    item.setQuantity(quantity);
	    cartItemRepository.save(item);
	}
	
	//removing item
	@Override
	public void removeItem(Long userId, Long productId) {

	    Cart cart = cartRepository.findByUserId(userId).get();

	    CartItem item = cartItemRepository
	            .findByCartIdAndProductId(cart.getId(), productId)
	            .orElseThrow(() -> new RuntimeException("Item not found"));

	    cartItemRepository.delete(item);
	}
	
	
	//getting cart items
	@Override
	public CartResponse getCartItems(Long userId) {

	    Cart cart = cartRepository.findByUserId(userId).get();

	     List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
	     
	    List<CartItemResponse> responses = items.stream().map(item -> {
	    	 CartItemResponse dto = new CartItemResponse();
	    	 dto.setProductId(item.getProduct().getId());
	    	 dto.setProductName(item.getProduct().getName());
	    	 dto.setPrice(item.getProduct().getPrice());
	    	 dto.setQuantity(item.getQuantity());
	    	 dto.setTotalPrice(item.getProduct().getPrice() * item.getQuantity());
	    	 return dto;
	     }).toList();
	     
	     
	     double total = responses.stream()
	    		        .mapToDouble(CartItemResponse::getTotalPrice)
	    		        .sum();
	     
	     CartResponse response = new CartResponse();
	     response.setItems(responses);
	     response.setTotalAmount(total);
	     
	     
	     return response;
	     
	}

	

}
