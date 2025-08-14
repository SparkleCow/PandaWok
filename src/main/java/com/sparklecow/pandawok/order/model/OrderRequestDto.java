package com.sparklecow.pandawok.order.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OrderRequestDto(

        @NotNull(message = "Product type is required")
        ProductType productType,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        Long amount,

        @Positive(message = "Client ID must be a positive number")
        Long clientId,

        @NotNull(message = "Delivery ID is required")
        @Positive(message = "Delivery ID must be a positive number")
        Long deliveryId,

        @NotBlank(message = "Address cannot be blank")
        @Size(max = 50, message = "Address cannot exceed 50 characters")
        String address,

        @NotBlank(message = "Client name is required")
        @Size(max = 50, message = "Client name cannot exceed 50 characters")
        String clientName,

        @Size(max = 255, message = "Additional information cannot exceed 255 characters")
        String additionalInformation
) {}
