package com.sparklecow.pandawok.user.controller;

import com.sparklecow.pandawok.user.model.LoginRequestDto;
import com.sparklecow.pandawok.user.model.LoginResponseDto;
import com.sparklecow.pandawok.user.model.UserRequestDto;
import com.sparklecow.pandawok.user.model.UserResponseDto;
import com.sparklecow.pandawok.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(authService.register(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}
