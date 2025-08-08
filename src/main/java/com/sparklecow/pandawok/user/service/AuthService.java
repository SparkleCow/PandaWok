package com.sparklecow.pandawok.user.service;

import com.sparklecow.pandawok.user.model.LoginRequestDto;
import com.sparklecow.pandawok.user.model.LoginResponseDto;
import com.sparklecow.pandawok.user.model.UserRequestDto;
import com.sparklecow.pandawok.user.model.UserResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    UserResponseDto register(UserRequestDto userRequestDto);
}
