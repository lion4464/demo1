package com.example.demo1.service;

import com.example.demo1.dto.AuthResponse;
import com.example.demo1.dto.LoginRequest;
import com.example.demo1.dto.RegisterRequest;
import com.example.demo1.entity.Role;
import com.example.demo1.entity.UserEntity;
import com.example.demo1.exceptions.BadCredentialsException;
import com.example.demo1.exceptions.DataNotFoundException;
import com.example.demo1.exceptions.UserAlreadyExistsException;
import com.example.demo1.generic.UserDetailsImpl;
import com.example.demo1.repository.UserRepository;
import com.example.demo1.utils.JwtUtils;
import com.example.demo1.utils.SecurityContextHolderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextHolderUtils securityContextHolderUtils;
    private final JwtUtils jwtUtils;
    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void register(RegisterRequest request) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email is already in use!");
        }

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) throws BadCredentialsException {
        Optional<UserEntity> optional = findByUsername(request.getUsername());
        if (optional.isEmpty() || !passwordEncoder.matches(request.getPassword(), optional.get().getPassword()))
            throw new BadCredentialsException(request.getUsername()+" couldn't find our db");
        UserDetailsImpl userDetails = generateUserDetail(optional.get());
        UserEntity user = userDetails.getUser();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        return AuthResponse.builder()
                .token(jwt)
                .type("Bearer")
                .id(user.getId())
                .build();
    }

    @Override
    public UserEntity findById(UUID userId) throws DataNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User not found with id: " + userId));

    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    private UserDetailsImpl generateUserDetail(UserEntity userEntity) {
        return UserDetailsImpl.build(userEntity);
    }
}
