package com.itschool.springapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// Data Transfer Object (DTO) for the User entity. Recommended for transferring data between the client and server.
// DTOs are often used to encapsulate and control the data that is sent over the network, keeping the entity hidden and intact.
public record UserDTO(@JsonProperty(access = JsonProperty.Access.READ_ONLY) // this annotation along with READ_ONLY access will make the id field read-only, meaning we don't provide it on request when creating a new UserDTO object
                      Long id,
                      String name,
                      String email,
                      Integer age,
                      AddressDTO address,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY) // read-only - don't provide it on request when creating/updating a new UserDTO object
                      List<OrderDTO> orders) {
}
