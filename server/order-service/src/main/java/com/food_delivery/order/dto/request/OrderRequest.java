package com.food_delivery.order.dto.request;

import com.food_delivery.order.entity.OrderStatus;
import com.food_delivery.order.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private BigDecimal totalPrice;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private List<PurchaseRequest> items;

}
