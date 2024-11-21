package com.example.demo1.convertor;

import com.example.demo1.dto.UserDto;
import com.example.demo1.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(UserEntity entity);
}
