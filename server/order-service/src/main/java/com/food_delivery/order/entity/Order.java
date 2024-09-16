package com.food_delivery.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {
    private Integer userId;
    private int numberOfItems;
    private BigDecimal totalPrice;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
