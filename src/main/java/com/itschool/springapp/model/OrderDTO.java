package com.itschool.springapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String id,
        String description) {
}
