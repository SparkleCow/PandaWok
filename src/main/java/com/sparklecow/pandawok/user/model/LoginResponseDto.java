package com.sparklecow.pandawok.user.model;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDto(
        @NotBlank
        String jwtToken
) {
}
