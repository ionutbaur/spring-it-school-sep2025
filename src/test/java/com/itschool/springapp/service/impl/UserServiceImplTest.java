package com.itschool.springapp.service.impl;

import com.itschool.springapp.repository.UserRepository;
import com.itschool.springapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final long USER_ID = 1L;

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void deleteUser() {
        // call the method we want to test
        userService.deleteUser(USER_ID);

        // since the above method is void and there is nothing returned to assert,
        // verify that the deleteById method of the UserRepository was called with the correct argument
        Mockito.verify(userRepository).deleteById(USER_ID);
    }
}