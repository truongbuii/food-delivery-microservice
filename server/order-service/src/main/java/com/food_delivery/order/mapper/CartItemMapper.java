package com.food_delivery.order.mapper;

import com.food_delivery.order.dto.response.CartItemResponse;
import com.food_delivery.order.dto.response.CartResponse;
import com.food_delivery.order.entity.Cart;
import com.food_delivery.order.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemResponse toCartItemResponse(CartItem cartItem);

    CartResponse toCartResponse(Cart cart);
}
