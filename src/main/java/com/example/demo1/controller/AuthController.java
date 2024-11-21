package com.example.demo1.controller;

import com.example.demo1.dto.AuthResponse;
import com.example.demo1.dto.LoginRequest;
import com.example.demo1.dto.RegisterRequest;
import com.example.demo1.exceptions.BadCredentialsException;
import com.example.demo1.exceptions.UserAlreadyExistsException;
import com.example.demo1.generic.ApiResponse;
import com.example.demo1.generic.GenericResponse;
import com.example.demo1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        userService.register(registerRequest);
        return ResponseEntity.ok(new GenericResponse("User registered successfully!",true));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) throws BadCredentialsException {
        return ApiResponse.ok(userService.login(request), "Login successful");
    }
}
