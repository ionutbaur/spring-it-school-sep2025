package com.itschool.springapp.controller;

import com.itschool.springapp.model.OrderDTO;
import com.itschool.springapp.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Swagger annotation to group the API endpoints under the "Order Manager" tag
@Tag(name = "Order Manager", description = "Order Manager API that manipulates operations related to orders")
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Place an order", description = "Place an order for a specific user and return the created order")
    @PostMapping("{userId}")
    public OrderDTO placeOrder(@Parameter(description = "The id of the user to place the order for") // Swagger annotation to describe the 'userId' parameter
                               @PathVariable long userId,
                               OrderDTO orderDTO) {
        return orderService.placeOrder(userId, orderDTO);
    }

    @Operation(summary = "Find all orders of a user", description = "Find all orders for a specific user and return the list of orders")
    @GetMapping("{userId}")
    public List<OrderDTO> getAllOrders(@Parameter(description = "The id of the user to search the orders for")
                                       @PathVariable Long userId) {
        return orderService.findAllOrders(userId);
    }

    @Operation(summary = "Find all orders by their description", description = "Find all orders for the given description and return the list of orders")
    @GetMapping("description/{description}")
    public List<OrderDTO> getOrdersByDescription(@PathVariable String description) {
        return orderService.findOrdersByDescription(description);
    }
}
