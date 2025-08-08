package com.sparklecow.pandawok.user.service;

import com.sparklecow.pandawok.user.entity.Role;
import com.sparklecow.pandawok.user.entity.User;
import com.sparklecow.pandawok.user.model.UserResponseDto;
import com.sparklecow.pandawok.user.model.UserUpdateDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    List<User> findByRole(Role role);

    User findByUsername(String username);
    User findByEmail(String email);
    User findById(Long id);

    UserResponseDto updateById(Long id, UserUpdateDto userUpdateDto);
    UserResponseDto updateByUsername(String username, UserUpdateDto userUpdateDto);
    UserResponseDto updateByEmail(String email, UserUpdateDto userUpdateDto);

    UserResponseDto updateOwnAccount(Authentication authentication, UserUpdateDto userUpdateDto);
}
