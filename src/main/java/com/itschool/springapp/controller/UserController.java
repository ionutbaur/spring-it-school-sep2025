package com.itschool.springapp.controller;

import com.itschool.springapp.model.UserDTO;
import com.itschool.springapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Swagger annotation to group and document the API endpoints under the "User Manager" tag
@Tag(name = "User Manager", description = "User Manager API that manipulates all the operations related to users")
@RestController // indicates that this class has the controll over a REST API and exposes its endpoints
@RequestMapping("users") // base URL path for all endpoints in this controller. All endpoints will start with "/users"
public class UserController {

    private final UserService userService;

    // dependency injection of UserService using constructor (recommended way)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // below we are exposing the endpoints of this API

    // Swagger annotation to document this endpoint
    @Operation(summary = "Find a user by id", description = "Find a user by their id and return the found user")
    @GetMapping("{id}") // maps to an HTTP request using GET method/verb, available at path <hostname>/users/<id>
    public UserDTO getUser(@PathVariable long id) { // suggests that the above {id} used in path should be bound to this input long param
        return userService.getUser(id); // call the method of the injected service with the captured param from endpoint's path
    }

    @Operation(summary = "Find all users", description = "Find all users in the database and return them in a list")
    @GetMapping
    public List<UserDTO> findAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Add a new user", description = "Add a new user to the database along with their address and return the created user")
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) { // suggests that the request body should be bound to this input UserDTO param
        return userService.createUser(userDTO);
    }

    @Operation(summary = "Update a user", description = "Update a user by their id and return the updated user")
    @PutMapping("{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @Operation(summary = "Delete a user", description = "Delete a user by their id. An HTTP 200 OK response is returned if the user was deleted successfully")
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
