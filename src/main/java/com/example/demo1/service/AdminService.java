package com.example.demo1.service;


import com.example.demo1.dto.ChangeRoleRequest;
import com.example.demo1.entity.UserEntity;
import com.example.demo1.exceptions.DataNotFoundException;

import java.util.UUID;

public interface AdminService {
    UserEntity changeUserRole(UUID userId, ChangeRoleRequest request) throws DataNotFoundException;
}
