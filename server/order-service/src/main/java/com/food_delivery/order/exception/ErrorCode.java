package com.food_delivery.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // common
    ERR_INTERNAL_SERVER_ERROR("Oops! Some things went wrong", HttpStatus.INTERNAL_SERVER_ERROR),
    ERR_TOKEN_EXPIRED("Your session has expired. Please log in again", HttpStatus.UNAUTHORIZED),
    ERR_TOKEN_INVALID("Session is invalid. Please log in again", HttpStatus.UNAUTHORIZED),
    ERR_UNAUTHENTICATED("You need to be logged in", HttpStatus.UNAUTHORIZED),
    ERR_UNAUTHORIZED("Sorry! You don't have permission", HttpStatus.FORBIDDEN),

    ERROR_CART_ITEM_NOT_FOUND("Cart item not found", HttpStatus.NOT_FOUND),
    ;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
