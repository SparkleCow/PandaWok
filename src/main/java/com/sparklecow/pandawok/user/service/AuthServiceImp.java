package com.sparklecow.pandawok.user.service;

import com.sparklecow.pandawok.config.jwt.JwtUtils;
import com.sparklecow.pandawok.user.entity.Role;
import com.sparklecow.pandawok.user.entity.User;
import com.sparklecow.pandawok.user.mapper.UserMapper;
import com.sparklecow.pandawok.user.model.*;
import com.sparklecow.pandawok.user.repository.RoleRepository;
import com.sparklecow.pandawok.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password());
        authenticationManager.authenticate(authentication);

        User user = userRepository.findByUsername(loginRequestDto.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new LoginResponseDto(jwtUtils.generateToken(user));
    }

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.username())) {
            throw new RuntimeException("Username already taken");
        }

        User user = userRepository.save(userMapper.toEntity(userRequestDto));

        return userMapper.toDto(user);
    }
}
