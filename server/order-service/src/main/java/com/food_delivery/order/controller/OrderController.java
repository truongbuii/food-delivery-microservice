package com.food_delivery.order.controller;


import com.food_delivery.order.dto.request.OrderRequest;
import com.food_delivery.order.dto.response.ApiResponse;
import com.food_delivery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/create")
    public ResponseEntity<ApiResponse<Integer>> createOrder(
            @RequestBody OrderRequest orderRequest
    ) {
        var response = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(
                ApiResponse.<Integer>builder()
                        .data(response)
                        .build());
    }
    
}
