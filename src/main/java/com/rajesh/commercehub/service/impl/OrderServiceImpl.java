package com.rajesh.commercehub.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rajesh.commercehub.dto.Orderresponse;
import com.rajesh.commercehub.entity.Cart;
import com.rajesh.commercehub.entity.CartItem;
import com.rajesh.commercehub.entity.Order;
import com.rajesh.commercehub.entity.OrderItem;
import com.rajesh.commercehub.repository.CartItemRepository;
import com.rajesh.commercehub.repository.CartRepository;
import com.rajesh.commercehub.repository.OrderItemRepository;
import com.rajesh.commercehub.repository.OrderRepository;
import com.rajesh.commercehub.service.OrderService;
import com.rajesh.commercehub.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	
	private final CartRepository cartRepository;
	
	private final CartItemRepository cartItemRepository;
	
	private final OrderRepository orderRepository;
	
	private final OrderItemRepository orderItemRepository;
	
	private final PaymentService paymentService;


	@Override
	public void placeOrder(Long userId) {
		
	//getting Cart
	Cart cart = cartRepository.findByUserId(userId)
			  .orElseThrow(() -> new RuntimeException("Cart not Found"));
	 
	     List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
	     
	     if(cartItems.isEmpty()) {
	    	 throw new RuntimeException("Cart is empty");
	     }
	     
	     //calculating total
	     double total = cartItems.stream()
	    		   .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
	    		   .sum();
	          
		 //calling payment (mock )
	     boolean paymentSuccess = paymentService.processPayment(userId , total);
	     
	     
	     if(!paymentSuccess) {
	    	 new RuntimeException("Payment Failed");
	     }
	     
	     
	     //Creating order
	     Order order = new Order();
	     order.setUser(cart.getUser());
	     order.setTotalAmount(total);
	     order.setOrderDate(LocalDateTime.now());
	     
	     Order savedOrder = orderRepository.save(order);
			
	     
	     //Creating order items
	     for(CartItem cartItem : cartItems) {
	    	 
	    	 OrderItem orderItem = new OrderItem();
	    	 orderItem.setOrder(savedOrder);
	    	 orderItem.setProduct(cartItem.getProduct());
	    	 orderItem.setQuantity(cartItem.getQuantity());
	    	 orderItem.setPrice(cartItem.getProduct().getPrice());
	    	 
	    	 orderItemRepository.save(orderItem);
	     }
	     
	     //clearing cart
	      cartItemRepository.deleteAll(cartItems);
	     
	}
	
	 
	//Orders history
	@Override
	public List<Orderresponse> getUserOrders(Long userId){
		
		List<Order> orders = orderRepository.findByUserId(userId);
		

	    return orders.stream()
	            .map(order -> Orderresponse.builder()
	                    .totalAmount(order.getTotalAmount())
	                    .orderDate(order.getOrderDate())
	                    .build())
	            .toList();

	}
	
	
}
