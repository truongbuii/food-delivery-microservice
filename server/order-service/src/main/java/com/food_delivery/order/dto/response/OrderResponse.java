package com.food_delivery.order.dto.response;

import com.food_delivery.order.entity.OrderStatus;
import com.food_delivery.order.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Integer userId;
    private int numberOfItems;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
}
