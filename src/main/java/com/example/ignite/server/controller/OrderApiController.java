package com.example.ignite.server.controller;

import com.example.common.dto.OrderDTO;
import com.example.ignite.server.model.Order;
import com.example.ignite.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
        * REST Controller to handle requests.
 */
@RestController
@RequestMapping("/api")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    // Order Endpoints
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setProduct(orderDTO.getProduct());
        order.setPrice(orderDTO.getPrice());
        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
