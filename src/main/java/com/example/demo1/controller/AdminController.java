package com.example.demo1.controller;

import com.example.demo1.convertor.UserMapper;
import com.example.demo1.dto.ChangeRoleRequest;
import com.example.demo1.dto.UserDto;
import com.example.demo1.exceptions.DataNotFoundException;
import com.example.demo1.generic.ApiResponse;
import com.example.demo1.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final UserMapper userMapper;

    @PutMapping("/users/{userId}/role")
    public ResponseEntity<ApiResponse<UserDto>> changeUserRole(
            @PathVariable UUID userId,
            @Valid @RequestBody ChangeRoleRequest request) throws DataNotFoundException {
        return ApiResponse.ok(
               userMapper.toDto(adminService.changeUserRole(userId, request)),
                "User role updated successfully"
        );
    }
}