package com.sparklecow.pandawok.order.controller;

import com.sparklecow.pandawok.order.model.OrderRequestDto;
import com.sparklecow.pandawok.order.model.OrderResponseDto;
import com.sparklecow.pandawok.order.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Endpoints for managing orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> findAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.listAllOrders(pageable));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid @RequestBody OrderRequestDto orderRequestDto
    ) {
        return ResponseEntity.ok(orderService.createOrder(orderRequestDto));
    }

    // List orders by client username
    @GetMapping("/client/username/{username}")
    public ResponseEntity<Page<OrderResponseDto>> findOrdersByClientUsername(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.listOrdersByClientUsername(username, pageable));
    }

    // List orders by client ID
    @GetMapping("/client/{clientId}")
    public ResponseEntity<Page<OrderResponseDto>> findOrdersByClient(
            @PathVariable Long clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.listOrdersByClient(clientId, pageable));
    }

    // List orders by delivery user
    @GetMapping("/delivery/{deliveryId}")
    public ResponseEntity<Page<OrderResponseDto>> findOrdersByDelivery(
            @PathVariable Long deliveryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.listOrdersByDelivery(deliveryId, pageable));
    }

    // List orders by status
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<OrderResponseDto>> findOrdersByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return switch (status.toUpperCase()) {
            case "COMPLETED" -> ResponseEntity.ok(orderService.listCompletedOrders(pageable));
            case "CANCELED" -> ResponseEntity.ok(orderService.listCanceledOrders(pageable));
            case "PENDING", "UNDONE" -> ResponseEntity.ok(orderService.listUndoneOrders(pageable));
            default -> ResponseEntity.badRequest().build();
        };
    }

    // Get single order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }
}
