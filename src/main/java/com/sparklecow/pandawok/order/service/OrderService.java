package com.sparklecow.pandawok.order.service;

import com.sparklecow.pandawok.order.model.OrderRequestDto;
import com.sparklecow.pandawok.order.model.OrderResponseDto;
import com.sparklecow.pandawok.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    Page<OrderResponseDto> listAllOrders(Pageable pageable);

    Page<OrderResponseDto> listOrdersByClient(User user, Pageable pageable);
    Page<OrderResponseDto> listOrdersByClientUsername(String username, Pageable pageable);

    Page<OrderResponseDto> listOrdersByDelivery(User user, Pageable pageable);

    Page<OrderResponseDto> listCompletedOrders(Pageable pageable);
    Page<OrderResponseDto> listCanceledOrders(Pageable pageable);
    Page<OrderResponseDto> listUndoneOrders(Pageable pageable);

    OrderResponseDto findById(Long id);
}
