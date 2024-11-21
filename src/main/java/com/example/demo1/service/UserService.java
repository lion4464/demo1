package com.example.demo1.service;


import com.example.demo1.dto.AuthResponse;
import com.example.demo1.dto.LoginRequest;
import com.example.demo1.dto.RegisterRequest;
import com.example.demo1.entity.UserEntity;
import com.example.demo1.exceptions.BadCredentialsException;
import com.example.demo1.exceptions.DataNotFoundException;
import com.example.demo1.exceptions.UserAlreadyExistsException;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

 Optional<UserEntity> findByUsername(String username);

    void register(RegisterRequest registerRequest) throws UserAlreadyExistsException;

    AuthResponse login(LoginRequest request) throws BadCredentialsException;

    UserEntity findById(UUID userId) throws DataNotFoundException;

    UserEntity save(UserEntity user);
}
