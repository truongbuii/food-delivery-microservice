package com.food_delivery.order.service;

import com.food_delivery.order.client.FoodService;
import com.food_delivery.order.dto.request.OrderRequest;
import com.food_delivery.order.dto.request.PurchaseRequest;
import com.food_delivery.order.dto.response.OrderResponse;
import com.food_delivery.order.entity.Order;
import com.food_delivery.order.entity.OrderItem;
import com.food_delivery.order.mapper.OrderMapper;
import com.food_delivery.order.repository.OrderItemRepository;
import com.food_delivery.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final FoodService foodService;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = (String) jwt.getClaims().get("id");
        var newOrder = orderMapper.toOrder(orderRequest);
        newOrder.setUserId(Integer.parseInt(userId));
        newOrder.setNumberOfItems(orderRequest.getItems().size());
        newOrder.setItems(new ArrayList<>());
        Order order = orderRepository.save(newOrder);
        for (PurchaseRequest purchase : orderRequest.getItems()) {
            var food = foodService.getFoodById(purchase.getProductId());
            if (food != null && food.getData() != null) {
                OrderItem newOrderItem = OrderItem.builder()
                        .order(order)
                        .productName(food.getData().getName())
                        .productPrice(food.getData().getPrice())
                        .quantity(purchase.getQuantity())
                        .build();
                log.info("newOrderItem: {}", newOrderItem);
                orderItemRepository.save(newOrderItem);
            }
        }
        return order.getId();
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Integer userId) {
        return null;
    }

    @Override
    public OrderResponse getOrderById(Integer orderId) {
        return null;
    }
}
