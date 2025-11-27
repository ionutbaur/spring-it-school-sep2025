package com.itschool.springapp.service.impl;

import com.itschool.springapp.entity.User;
import com.itschool.springapp.model.UserDTO;
import com.itschool.springapp.repository.UserRepository;
import com.itschool.springapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Annotation to tell Spring that this is a bean of type Service (usually contains business logic)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) { // inject the repository handling the 'User' entity
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUser(long id) {
        User foundUserEntity = userRepository.findById(id) // find the User entity by its ID in the database
                .orElseThrow(); // if not found, throw an error

        return toUserDTO(foundUserEntity); // convert the User entity to UserDTO and return it
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUserEntities = userRepository.findAll(); // retrieve all User entities from the database

        return allUserEntities.stream()
                .map(this::toUserDTO)
                .toList(); // convert each User entity to UserDTO and collect them into a list
    }

    @Override
    public UserDTO createUser(UserDTO newUserDTO) {
        User userEntity = toUserEntity(newUserDTO); // convert the incoming UserDTO needed for creation to a User entity

        // save the new User entity in the database
        User createdUserEntity = userRepository.save(userEntity); // since the provided User entity does not have an ID, it will CREATE a new record in the database

        return toUserDTO(createdUserEntity); // convert the saved User entity back to UserDTO and return it (may contain generated fields like ID)
    }

    @Override
    public UserDTO updateUser(long id, UserDTO updatedUserDTO) {
        User userEntity = toUserEntity(updatedUserDTO); // convert the incoming UserDTO with updated info to a User entity
        userEntity.setId(id); // set the ID of the User entity to ensure we are updating the correct record

        // save the updated User entity in the database
        User updatedUserEntity = userRepository.save(userEntity); // since the User entity with this ID already exists, it will perform an UPDATE in the database

        return toUserDTO(updatedUserEntity);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id); // delete the User entity with the specified ID from the database
    }

    // helper method to convert User entity to UserDTO
    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAge());
    }

    // helper method to convert UserDTO to User entity
    private User toUserEntity(UserDTO userDTO) {
        return new User(userDTO.name(), userDTO.email(), userDTO.age());
    }
}
