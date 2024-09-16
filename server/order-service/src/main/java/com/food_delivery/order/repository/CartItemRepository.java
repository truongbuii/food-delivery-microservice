package com.food_delivery.order.repository;

import com.food_delivery.order.entity.Cart;
import com.food_delivery.order.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Set<CartItem> findAllByCart(Cart cart);
}
