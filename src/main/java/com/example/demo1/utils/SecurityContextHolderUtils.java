package com.example.demo1.utils;

import com.example.demo1.generic.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityContextHolderUtils {
    public UserDetailsImpl getCurrentUser(){
        log.info("SecurityContextHolderUtils is getting current user");
        return (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}
