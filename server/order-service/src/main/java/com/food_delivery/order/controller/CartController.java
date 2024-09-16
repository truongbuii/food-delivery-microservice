package com.food_delivery.order.controller;

import com.food_delivery.order.dto.request.CartItemRequest;
import com.food_delivery.order.dto.response.ApiResponse;
import com.food_delivery.order.dto.response.CartResponse;
import com.food_delivery.order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart/{userId}")
    public ResponseEntity<ApiResponse<CartResponse>> getCart(
            @PathVariable("userId") Integer userId
    ) {
        var cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(ApiResponse.<CartResponse>builder()
                .data(cart)
                .build());
    }

    @PostMapping("/cart/add-item")
    public ResponseEntity<ApiResponse<CartResponse>> addToCart(
            @RequestBody CartItemRequest cartItemRequest
    ) {
        var cart = cartService.addToCart(cartItemRequest);
        return ResponseEntity.ok(ApiResponse.<CartResponse>builder()
                .data(cart)
                .build());
    }
    
    @DeleteMapping("/cart/remove-item")
    public ResponseEntity<ApiResponse<CartResponse>> removeCartItem(
            @RequestParam Integer itemId
    ) {
        cartService.removeCartItem(itemId);
        return ResponseEntity.ok(ApiResponse.<CartResponse>builder()
                .message("Cart item removed successfully")
                .build());
    }

    @PutMapping("/cart/clear-cart")
    public ResponseEntity<ApiResponse<CartResponse>> clearCart(
            @RequestParam Integer userId
    ) {
        cartService.clearCart(userId);
        return ResponseEntity.ok(ApiResponse.<CartResponse>builder()
                .message("Cart cleared successfully")
                .build());
    }
}
