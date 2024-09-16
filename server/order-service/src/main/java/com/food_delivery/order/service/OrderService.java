package com.food_delivery.order.service;

import com.food_delivery.order.dto.request.OrderRequest;
import com.food_delivery.order.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    Integer createOrder(OrderRequest orderRequest);

    List<OrderResponse> getOrdersByUserId(Integer userId);

    OrderResponse getOrderById(Integer orderId);
}
