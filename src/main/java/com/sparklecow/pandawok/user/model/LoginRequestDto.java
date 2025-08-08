package com.sparklecow.pandawok.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @NotBlank(message = "Username is mandatory")
        @Size(min = 3, max = 20)
        String username,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 20)
        String password
) {
}
