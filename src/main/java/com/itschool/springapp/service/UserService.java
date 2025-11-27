package com.itschool.springapp.service;

import com.itschool.springapp.model.UserDTO;

import java.util.List;

// "contract" for user-related operations
public interface UserService {

    /**
     * Retrieve a specific user
     * @param id the id of the user to retrieve
     * @return a user deserialized into a {@link UserDTO} object
     */
    UserDTO getUser(long id);

    /**
     * Retrieve all users
     * @return a list of {@link UserDTO} objects representing all users
     */
    List<UserDTO> getAllUsers();

    /**
     * Create a new user
     * @param newUserDTO a {@link UserDTO} object representing the new user to create
     * @return the created user as a {@link UserDTO} object
     */
    UserDTO createUser(UserDTO newUserDTO);

    /**
     * Update an existing user
     * @param id the id of the user to update
     * @param updatedUserDTO a {@link UserDTO} object representing the user data to update
     * @return the updated user as a {@link UserDTO} object
     */
    UserDTO updateUser(long id, UserDTO updatedUserDTO);

    /**
     * Delete a user
     * @param id the id of the user to delete
     */
    void deleteUser(long id);
}
