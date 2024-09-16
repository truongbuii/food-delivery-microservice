package com.food_delivery.order.client;

import com.food_delivery.order.dto.response.ApiResponse;
import com.food_delivery.order.dto.response.FoodResponse;
import com.food_delivery.order.security.AuthenticationRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "restaurant-service",
        configuration = {AuthenticationRequestInterceptor.class}
)
public interface FoodService {
    @GetMapping(
            value = "/restaurant/external/get/food/{foodId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ApiResponse<FoodResponse> getFoodById(
            @PathVariable("foodId") String foodId
    );
}
