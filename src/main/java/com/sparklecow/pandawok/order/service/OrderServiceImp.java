package com.sparklecow.pandawok.order.service;

import com.sparklecow.pandawok.order.model.OrderRequestDto;
import com.sparklecow.pandawok.order.model.OrderResponseDto;
import com.sparklecow.pandawok.order.model.Status;
import com.sparklecow.pandawok.order.repository.OrderRepository;
import com.sparklecow.pandawok.order.entity.Order;
import com.sparklecow.pandawok.order.mapper.OrderMapper;
import com.sparklecow.pandawok.user.entity.User;
import com.sparklecow.pandawok.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        log.info("Creating new order for user {}", orderRequestDto.clientName());

        User delivery = userRepository.findById(orderRequestDto.deliveryId()).orElseThrow(() -> new RuntimeException(""));
        User client = Optional.ofNullable(orderRequestDto.clientId()).flatMap(userRepository::findById).orElse(null);

        Order order = OrderMapper.toEntity(orderRequestDto, client, delivery);
        Order savedOrder = orderRepository.save(order);

        log.info("Order created with ID: {}", savedOrder.getId());
        return OrderMapper.toResponseDto(savedOrder);
    }

    @Override
    public Page<OrderResponseDto> listAllOrders(Pageable pageable) {
        log.info("Listing all orders");
        return orderRepository.findAll(pageable)
                .map(OrderMapper::toResponseDto);
    }

    @Override
    public Page<OrderResponseDto> listOrdersByClient(Long clientId, Pageable pageable) {
        User client = userRepository.findById(clientId).orElseThrow(() -> new RuntimeException(""));

        log.info("Listing orders for client: {}", client.getUsername());
        return orderRepository.findByClient(client, pageable)
                .map(OrderMapper::toResponseDto);
    }

    @Override
    public Page<OrderResponseDto> listOrdersByClientUsername(String username, Pageable pageable) {
        log.info("Listing orders for client username: {}", username);
        return orderRepository.findByClientName(username, pageable)
                .map(OrderMapper::toResponseDto);
    }

    @Override
    public Page<OrderResponseDto> listOrdersByDelivery(Long deliveryId, Pageable pageable) {
        User delivery = userRepository.findById(deliveryId).orElseThrow(() -> new RuntimeException(""));

        log.info("Listing orders for delivery user: {}", delivery.getUsername());
        return orderRepository.findByDelivery(delivery, pageable)
                .map(OrderMapper::toResponseDto);
    }

    @Override
    public Page<OrderResponseDto> listCompletedOrders(Pageable pageable) {
        log.info("Listing completed orders");
        return orderRepository.findByStatus(Status.COMPLETED, pageable)
                .map(OrderMapper::toResponseDto);
    }

    @Override
    public Page<OrderResponseDto> listCanceledOrders(Pageable pageable) {
        log.info("Listing canceled orders");
        return orderRepository.findByStatus(Status.CANCELED, pageable)
                .map(OrderMapper::toResponseDto);
    }

    @Override
    public Page<OrderResponseDto> listUndoneOrders(Pageable pageable) {
        log.info("Listing pending orders");
        return orderRepository.findByStatus(Status.UNDONE, pageable)
                .map(OrderMapper::toResponseDto);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        log.info("Searching for order with ID: {}", id);
        return orderRepository.findById(id)
                .map(OrderMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
