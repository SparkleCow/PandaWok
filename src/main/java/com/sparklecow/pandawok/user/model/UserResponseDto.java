package com.sparklecow.pandawok.user.model;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponseDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<String> roles
) {}