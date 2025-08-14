package com.sparklecow.pandawok.order.mapper;

import com.sparklecow.pandawok.order.entity.Order;
import com.sparklecow.pandawok.order.model.OrderResponseDto;
import com.sparklecow.pandawok.order.model.OrderRequestDto;
import com.sparklecow.pandawok.order.model.Status;
import com.sparklecow.pandawok.user.entity.User;

public class OrderMapper {

    private OrderMapper(){}

    public static Order toEntity(OrderRequestDto dto, User client, User delivery) {
        if (dto == null) {
            return null;
        }

        return Order.builder()
                .productType(dto.productType())
                .status(Status.UNDONE)
                .amount(dto.amount())
                .client(client)
                .delivery(delivery)
                .address(dto.address())
                .clientName(dto.clientName())
                .additionalInformation(dto.additionalInformation())
                .build();
    }

    public static OrderResponseDto toResponseDto(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponseDto(
                order.getId(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getProductType(),
                order.getStatus(),
                order.getAmount(),
                order.getClientName(),
                order.getDelivery() != null ? order.getDelivery().getName() : null,
                order.getAddress(),
                order.getAdditionalInformation()
        );
    }
}
