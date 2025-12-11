package com.itschool.springapp.service;

import com.itschool.springapp.model.OrderDTO;

import java.util.List;

public interface OrderService {

    /**
     * Retrieve all orders for a specific user
     * @param userId the id of the user whose orders to retrieve
     * @return a list of {@link OrderDTO} objects representing all orders for the user
     */
    List<OrderDTO> findAllOrders(long userId);

    /**
     * Place a new order for a specific user
     * @param userId the id of the user placing the order
     * @param orderDTO a {@link OrderDTO} object representing the order to place
     * @return the placed order as a {@link OrderDTO} object
     */
    OrderDTO placeOrder(long userId, OrderDTO orderDTO);

    /**
     * Find orders by their description
     * @param description the description to search for
     * @return a list of {@link OrderDTO} objects matching the description
     */
    List<OrderDTO> findOrdersByDescription(String description);
}
