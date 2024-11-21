package com.example.demo1.configuration;

import com.example.demo1.generic.UserDetailsImpl;
import com.example.demo1.utils.SecurityContextHolderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class JPAAuditConfig {
    private final SecurityContextHolderUtils securityContextHolderUtils;
    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> {

                if(Objects.nonNull(securityContextHolderUtils.getCurrentUser())) {
                    UserDetailsImpl userDetails = securityContextHolderUtils.getCurrentUser();
                    return Optional.ofNullable(userDetails.getUserId());
                }
            return Optional.empty();
        };
    }
}
