package com.food_delivery.order.service;

import com.food_delivery.order.dto.request.CartItemRequest;
import com.food_delivery.order.dto.request.CartItemUpdate;
import com.food_delivery.order.dto.response.CartItemResponse;
import com.food_delivery.order.dto.response.CartResponse;
import com.food_delivery.order.entity.Cart;
import com.food_delivery.order.entity.CartItem;
import com.food_delivery.order.exception.ErrorCode;
import com.food_delivery.order.exception.ResourceNotFoundException;
import com.food_delivery.order.mapper.CartItemMapper;
import com.food_delivery.order.repository.CartItemRepository;
import com.food_delivery.order.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartResponse getCartByUserId(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            return null;
        }
        Set<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(cartItems.stream().map(cartItemMapper::toCartItemResponse).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public CartResponse addToCart(CartItemRequest cartItemRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = (String) jwt.getClaims().get("id");
        Cart cart = cartRepository.findByUserId(Integer.parseInt(userId));
        Set<CartItem> existingCartItem = new HashSet<>();
        if (cart == null) {
            cart = Cart.builder()
                    .userId(Integer.parseInt(userId))
                    .items(existingCartItem)
                    .build();
            cartRepository.save(cart);
        } else {
            existingCartItem = cartItemRepository.findAllByCart(cart);
        }
        Optional<CartItem> getCartItem = existingCartItem.stream()
                .filter(cartItem -> cartItem.getProductId().equals(cartItemRequest.getProductId()))
                .findFirst();
        if (getCartItem.isPresent()) {
            getCartItem.get().setQuantity(getCartItem.get().getQuantity() + cartItemRequest.getQuantity());
            cartItemRepository.save(getCartItem.get());
        } else {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .productId(cartItemRequest.getProductId())
                    .quantity(cartItemRequest.getQuantity())
                    .build();
            cartItemRepository.save(cartItem);
        }
        return getCartByUserId(Integer.parseInt(userId));
    }

    @Override
    public CartItemResponse updateCart(CartItemUpdate cartItemUpdate, Integer userId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemUpdate.getCartItemId());
        if (cartItemOptional.isEmpty()) {
            throw new ResourceNotFoundException(ErrorCode.ERROR_CART_ITEM_NOT_FOUND);
        }
        cartItemOptional.get().setQuantity(cartItemUpdate.getQuantity());
        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItemOptional.get()));
    }

    @Override
    public void removeCartItem(Integer cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new ResourceNotFoundException(ErrorCode.ERROR_CART_ITEM_NOT_FOUND);
        }
        cartItemRepository.delete(cartItemOptional.get());
    }

    @Override
    public void clearCart(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}

