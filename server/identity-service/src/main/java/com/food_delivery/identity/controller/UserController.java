package com.food_delivery.identity.controller;

import com.food_delivery.identity.dto.request.user.VerificationEmailRequest;
import com.food_delivery.identity.dto.response.ApiResponse;
import com.food_delivery.identity.dto.response.UserResponse;
import com.food_delivery.identity.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/verification-email")
    public ResponseEntity<ApiResponse<UserResponse>> verifyEmail(
            @Valid
            @RequestBody VerificationEmailRequest verificationEmailRequest
    ) {
        var user = userService.verificationEmail(verificationEmailRequest);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder().data(user).build());
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(
            @PathVariable("userId") Integer userId
    ) {
        var user = userService.getUser(userId);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder().data(user).build());
    }
}
