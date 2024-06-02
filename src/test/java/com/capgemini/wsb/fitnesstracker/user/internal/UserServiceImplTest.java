package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link UserServiceImpl}
 */
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    /**
     * Set up mocks
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test that a user can be created
     */
    @Test
    void shouldCreateUser() {
        User user = new User("Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    /**
     * Test that a user can be retrieved
     */
    @Test
    void shouldGetUser() {
        User user = new User("Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUser(1L);

        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(1L);
    }

    /**
     * Test that a user can be retrieved by email
     */
    @Test
    void shouldGetUserByEmail() {
        User user = new User("Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByEmail("Emma.Johnson@domain.com");

        assertEquals(user, result.get());
        verify(userRepository, times(1)).findByEmail("Emma.Johnson@domain.com");
    }

}