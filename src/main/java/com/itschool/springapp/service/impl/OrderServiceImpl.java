package com.itschool.springapp.service.impl;

import com.itschool.springapp.entity.Order;
import com.itschool.springapp.entity.User;
import com.itschool.springapp.exception.UserNotFoundException;
import com.itschool.springapp.model.OrderDTO;
import com.itschool.springapp.repository.OrderRepository;
import com.itschool.springapp.repository.UserRepository;
import com.itschool.springapp.service.OrderService;
import com.itschool.springapp.utils.ModelConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderDTO> findAllOrders(long userId) {
        return orderRepository.findOrdersByUser_Id(userId)
                .stream()
                .map(ModelConverter::toOrderDTO)
                .toList();
    }

    @Override
    public OrderDTO placeOrder(long userId, OrderDTO orderDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User " + userId + " not found in db!"));
        Order order = new Order(orderDTO.description(), user);
        Order createdOrder = orderRepository.save(order);

        return ModelConverter.toOrderDTO(createdOrder);
    }

    @Override
    public List<OrderDTO> findOrdersByDescription(String description) {
        return orderRepository.findOrdersByDescription(description)
                .stream()
                .map(ModelConverter::toOrderDTO)
                .toList();
    }

}
