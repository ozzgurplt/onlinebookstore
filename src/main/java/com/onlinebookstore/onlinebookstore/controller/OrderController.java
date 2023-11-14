package com.onlinebookstore.onlinebookstore.controller;

import com.onlinebookstore.onlinebookstore.entities.Order;
import com.onlinebookstore.onlinebookstore.entities.rest.OrderDetails;
import com.onlinebookstore.onlinebookstore.entities.rest.OrderRequest;
import com.onlinebookstore.onlinebookstore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order newOrder = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        List<Order> userOrders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(userOrders);
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable Long orderId) {
        OrderDetails orderDetails = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderDetails);
    }
}
