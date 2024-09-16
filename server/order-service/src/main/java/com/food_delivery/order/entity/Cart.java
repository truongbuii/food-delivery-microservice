package com.food_delivery.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cart extends BaseEntity {
    private Integer userId;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItem> items;
}
