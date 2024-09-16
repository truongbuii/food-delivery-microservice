package com.food_delivery.order.service;

import com.food_delivery.order.dto.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponse> getAllByOrderId(Integer orderId);
}
