package com.food_delivery.order.service;

import com.food_delivery.order.client.FoodService;
import com.food_delivery.order.dto.response.OrderItemResponse;
import com.food_delivery.order.mapper.OrderMapper;
import com.food_delivery.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final FoodService foodService;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderItemResponse> getAllByOrderId(Integer orderId) {
        return orderItemRepository.findAllByOrderId(orderId).stream()
                .map(orderMapper::toOrderItemResponse)
                .toList();
    }
}
