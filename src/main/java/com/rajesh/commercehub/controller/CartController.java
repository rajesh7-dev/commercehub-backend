package com.rajesh.commercehub.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.commercehub.dto.CartResponse;
import com.rajesh.commercehub.service.CartService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long userId,
                           @RequestParam Long productId,
                           @RequestParam int quantity) {

        cartService.addToCart(userId, productId, quantity);
        return "Item added to cart";
    }

    @PutMapping("/update")
    public String updateQuantity(@RequestParam Long userId,
                                @RequestParam Long productId,
                                @RequestParam int quantity) {

        cartService.updateQuantity(userId, productId, quantity);
        return "Quantity updated";
    }

    @DeleteMapping("/remove")
    public String removeItem(@RequestParam Long userId,
                            @RequestParam Long productId) {

        cartService.removeItem(userId, productId);
        return "Item removed";
    }

    @GetMapping
    public CartResponse getCart(@RequestParam Long userId) {
        return cartService.getCartItems(userId);
    }
}
