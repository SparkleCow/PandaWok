package com.sparklecow.pandawok.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank
        String username,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        @Size(min = 8) String password,
        @Email
        @NotBlank String email,
        String phone,
        String address
) {}