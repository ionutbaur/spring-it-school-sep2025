package com.itschool.springapp.model;

// Data Transfer Object (DTO) for the User entity. Recommended for transferring data between the client and server.
// DTOs are often used to encapsulate and control the data that is sent over the network, keeping the entity hidden and intact.
public record UserDTO(Long id, String name, String email, Integer age) {
}
