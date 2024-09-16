package com.food_delivery.order.mapper;

import com.food_delivery.order.dto.request.OrderItemRequest;
import com.food_delivery.order.dto.request.OrderRequest;
import com.food_delivery.order.dto.response.OrderItemResponse;
import com.food_delivery.order.entity.Order;
import com.food_delivery.order.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderRequest orderRequest);

    OrderItem toOrderItem(OrderItemRequest orderItemRequest);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
