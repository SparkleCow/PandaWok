package com.sparklecow.pandawok.user.service;

import com.sparklecow.pandawok.config.jwt.JwtUtils;
import com.sparklecow.pandawok.user.model.LoginRequestDto;
import com.sparklecow.pandawok.user.model.UserRequestDto;
import com.sparklecow.pandawok.user.model.UserResponseDto;
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

    @Override
    public LoginRequestDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequestDto.password(), loginRequestDto.username());
        authenticationManager.authenticate(authentication);

    }

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        return null;
    }
}
