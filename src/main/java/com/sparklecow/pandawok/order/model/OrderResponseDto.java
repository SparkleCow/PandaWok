package com.sparklecow.pandawok.order.model;

import java.time.LocalDateTime;

public record OrderResponseDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        ProductType productType,
        Status status,
        Long amount,
        String clientName,
        String deliveryName,
        String address,
        String additionalInformation
) {}