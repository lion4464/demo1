package com.example.demo1.service;

import com.example.demo1.dto.ChangeRoleRequest;
import com.example.demo1.entity.UserEntity;
import com.example.demo1.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final UserService userService;
    @Override
    public UserEntity changeUserRole(UUID userId, ChangeRoleRequest request) throws DataNotFoundException {
        UserEntity user = userService.findById(userId);
        user.setRole(request.getRole());
        return userService.save(user);
    }
}
