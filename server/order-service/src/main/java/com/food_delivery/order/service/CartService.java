package com.food_delivery.order.service;

import com.food_delivery.order.dto.request.CartItemRequest;
import com.food_delivery.order.dto.request.CartItemUpdate;
import com.food_delivery.order.dto.response.CartItemResponse;
import com.food_delivery.order.dto.response.CartResponse;

public interface CartService {
    CartResponse getCartByUserId(Integer userId);

    CartResponse addToCart(CartItemRequest cartItemRequest);

    CartItemResponse updateCart(CartItemUpdate cartItemUpdate, Integer userId);

    void removeCartItem(Integer cartItemId);

    void clearCart(Integer userId);
}
