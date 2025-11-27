package com.itschool.springapp.controller;

import com.itschool.springapp.model.UserDTO;
import com.itschool.springapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // indicates that this class has the controll over a REST API and exposes its endpoints
@RequestMapping("users") // base URL path for all endpoints in this controller. All endpoints will start with "/users"
public class UserController {

    private final UserService userService;

    // dependency injection of UserService using constructor (recommended way)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // below we are exposing the endpoints of this API

    // maps to an HTTP request using GET method/verb, available at path <hostname>/users/<id>
    @GetMapping("{id}")
    public UserDTO getUser(@PathVariable long id) { // suggests that the above {id} used in path should be bound to this input long param
        return userService.getUser(id); // call the method of the injected service with the captured param from endpoint's path
    }

    @GetMapping
    public List<UserDTO> findAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) { // suggests that the request body should be bound to this input UserDTO param
        return userService.createUser(userDTO);
    }

    @PutMapping("{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
