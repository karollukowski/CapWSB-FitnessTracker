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
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Mock
    UserServiceImpl userService;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        User user = new User("Black", "Beard", LocalDate.now(), "blackbeard@example.com");
        UserDto userDto = new UserDto(1L, "Black", "Beard", LocalDate.now(), "blackbeard@example.com");
        List<User> users = Collections.singletonList(user);

        when(userService.findAllUsers()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        mockMvc.perform(get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).findAllUsers();
        verify(userMapper, times(1)).toDto(user);}

}