package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for UserController
 */
class UserControllerTest {

    @Mock
    UserServiceImpl userService;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserController userController;

    private MockMvc mockMvc;

    /**
     * Set up mocks
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * Test that all users can be retrieved
     * @throws Exception
     */
    @Test
    void shouldGetAllUsers() throws Exception {
        User user = new User("Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");
        UserDto userDto = new UserDto(1L, "Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");
        List<User> users = Collections.singletonList(user);

        when(userService.findAllUsers()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        mockMvc.perform(get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).findAllUsers();
        verify(userMapper, times(1)).toDto(user);}

    /**
     * Test that a user can be retrieved by id
     * @throws Exception
     */
    @Test
    void shouldGetUserById() throws Exception {
        User user = new User("Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");
        UserDto userDto = new UserDto(1L, "Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");

        when(userService.getUser(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        mockMvc.perform(get("/v1/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).getUser(1L);
        verify(userMapper, times(1)).toDto(user);
    }

    /**
     * Test that a user can be retrieved by email
     * @throws Exception
     */
    @Test
    void shouldGetUserByEmail() throws Exception {
        User user = new User("Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");
        UserDto userDto = new UserDto(1L, "Emma", "Johnson", LocalDate.now(), "Emma.Johnson@domain.com");

        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        mockMvc.perform(get("/v1/users/email/{email}", "Emma.Johnson@domain.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).getUserByEmail("Emma.Johnson@domain.com");
        verify(userMapper, times(1)).toDto(user);
    }
}

