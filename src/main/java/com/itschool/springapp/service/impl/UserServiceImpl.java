package com.itschool.springapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.springapp.entity.User;
import com.itschool.springapp.exception.UserNotFoundException;
import com.itschool.springapp.model.UserDTO;
import com.itschool.springapp.repository.UserRepository;
import com.itschool.springapp.service.UserService;
import com.itschool.springapp.utils.ModelConverter;
import com.itschool.springapp.utils.ObjectMapperSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.*;

@Service // Annotation to tell Spring that this is a bean of type Service (usually contains business logic)
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) { // inject the repository handling the 'User' entity
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUser(long id) {
        User foundUserEntity = userRepository.findById(id) // find the User entity by its ID in the database
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found in db!")); // if not found, throw a custom exception

        return ModelConverter.toUserDTO(foundUserEntity); // convert the User entity to UserDTO and return it
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // move the blocking database call to a virtual thread to avoid blocking the main thread
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            // define a Callable task to retrieve all users from the database
            Callable<List<UserDTO>> task = () -> {
                LOGGER.info("Accessing DB userRepository.findAll() on thread {}", Thread.currentThread());
                List<User> allUserEntities = userRepository.findAll(); // retrieve all User entities from the database

                return allUserEntities.stream()
                        .map(ModelConverter::toUserDTO)
                        .toList(); // convert each User entity to UserDTO and collect them into a list
            };

            // submit the task on the virtual thread executor service
            Future<List<UserDTO>> futureList = executorService.submit(task);

            // wait for the task to complete and get the result
            List<UserDTO> allUsers = futureList.get();
            logSerializedObj(allUsers); // optional, demonstrates the use of ObjectMapper to serialize the list to JSON and log it

            return allUsers;
        } catch (ExecutionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while executing the task that retrieves all users", e);
        } catch (InterruptedException e) {
            // interrupt the current thread if it gets stuck
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "InterruptedException occurred in current thread", e);
        }
    }

    @Override
    public UserDTO createUser(UserDTO newUserDTO) {
        User userEntity = ModelConverter.toUserEntity(newUserDTO); // convert the incoming UserDTO needed for creation to a User entity

        // save the new User entity in the database
        User createdUserEntity = userRepository.save(userEntity); // since the provided User entity does not have an ID, it will CREATE a new record in the database

        return ModelConverter.toUserDTO(createdUserEntity); // convert the saved User entity back to UserDTO and return it (may contain generated fields like ID)
    }

    @Override
    public UserDTO updateUser(long id, UserDTO updatedUserDTO) {
        User userEntity = ModelConverter.toUserEntity(updatedUserDTO); // convert the incoming UserDTO with updated info to a User entity
        userEntity.setId(id); // set the ID of the User entity to ensure we are updating the correct record

        // save the updated User entity in the database
        User updatedUserEntity = userRepository.save(userEntity); // since the User entity with this ID already exists, it will perform an UPDATE in the database

        return ModelConverter.toUserDTO(updatedUserEntity);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id); // delete the User entity with the specified ID from the database
    }

    // a generic method to serialize any object to JSON and log it (for demo purposes)
    private <T> void logSerializedObj(T obj) {
        ObjectMapper objectMapper = ObjectMapperSingleton.getInstance(); // obtain the ObjectMapper unique instance
        try {
            // serialize the object to JSON string
            String json = objectMapper.writeValueAsString(obj);
            LOGGER.info("Serialized type to JSON: {}", json);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error serializing type to JSON", e);
        }
    }

}
