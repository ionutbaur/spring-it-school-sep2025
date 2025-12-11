package com.itschool.springapp.utils;

import com.itschool.springapp.entity.Address;
import com.itschool.springapp.entity.Order;
import com.itschool.springapp.entity.User;
import com.itschool.springapp.model.AddressDTO;
import com.itschool.springapp.model.OrderDTO;
import com.itschool.springapp.model.UserDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ModelConverter {

    private ModelConverter() {
        // utility class, prohibit instantiation from external - its methods should be accessed only static
    }

    // helper method to convert User entity to UserDTO
    public static UserDTO toUserDTO(User userEntity) {
        Address addressEntity = userEntity.getAddress(); // get the Address entity from User entity
        AddressDTO addressDTO = toAddressDTO(addressEntity); // convert to DTO

        List<Order> orderEntities = userEntity.getOrders(); // get the list of Order entities from User entity

        // if orderEntities is null, return empty list - traditional style of checking the orderDTOs for null,
        // then functional style of mapping each element to OrderDTO
        List<OrderDTO> orderDTOs = Collections.emptyList();
        if (orderEntities != null) {
            orderDTOs = orderEntities.stream()
                    .map(ModelConverter::toOrderDTO)
                    .toList();
        }

        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail(),
                userEntity.getAge(), addressDTO, orderDTOs);
    }

    // helper method to convert UserDTO to User entity
    public static User toUserEntity(UserDTO userDTO) {
        AddressDTO addressDTO = userDTO.address();
        Address addressEntity = toAddressEntity(addressDTO);

        List<OrderDTO> orderDTOs = userDTO.orders();

        // if 'orderDTOs' is null, return empty list - same as checking 'orderEntities' for null in the above 'toUserDTO' method,
        // but everything in nested functional style
        List<Order> orderEntities = Optional.ofNullable(orderDTOs) // potentially null 'orderDTOs' list - if 'orderDTOs' is null jump directly to 'orElse'; if not null, below map is executed
                .map(orders -> orders.stream() // if the Optional is present ('orderDTOs' list not null) map the list to something else using stream
                        .map(ModelConverter::toOrderEntity) // map each OrderDTO element from the above stream to an Order entity (convert it)
                        .toList()) // collect the stream to a new list - will be a list of Order entities because of the previous map
                .orElse(Collections.emptyList()); // if the Optional is empty ('orderDTOs' list is null), return an empty list

        return new User(userDTO.name(), userDTO.email(), userDTO.age(), addressEntity, orderEntities);
    }

    public static AddressDTO toAddressDTO(Address addressEntity) {
        /*if (addressEntity == null) {
            return null;
        }

        return new AddressDTO(addressEntity.getCity(), addressEntity.getStreet(), addressEntity.getNumber(), addressEntity.getZipCode());*/

        // same as above commented but in functional style using Optional
        return Optional.ofNullable(addressEntity)
                .map(address -> new AddressDTO(address.getCity(), address.getStreet(), address.getNumber(), address.getZipCode()))
                .orElse(null);
    }

    public static Address toAddressEntity(AddressDTO addressDTO) {
        return Optional.ofNullable(addressDTO)
                .map(address -> new Address(address.city(), address.street(), address.number(), address.zipCode()))
                .orElse(null);
    }

    public static OrderDTO toOrderDTO(Order orderEntity) {
        return Optional.ofNullable(orderEntity)
                .map(order -> new OrderDTO(order.getId(), order.getDescription()))
                .orElse(null);
    }

    public static Order toOrderEntity(OrderDTO orderDTO) {
        return Optional.ofNullable(orderDTO)
                .map(order -> new Order(order.description()))
                .orElse(null);
    }
}
