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

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUser() {
        User user = new User("Black", "Beard", LocalDate.now(), "blackbeard@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldGetUser() {
        User user = new User("Black", "Beard", LocalDate.now(), "blackbeard@example.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUser(1L);

        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(1L);
    }

}