package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // TODO: saveUser with Service and return User
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping
    public User updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userService.updateUser(user);
    }

    @GetMapping("/search/email")
    public List<UserDto> findUsersByEmail(@RequestParam String email) {
        return userService.findUsersByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/search/name")
    public List<UserDto> findUsersByNameFragment(@RequestParam String nameFragment) {
        return userService.findUsersByNameFragment(nameFragment)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/search/age")
    public List<UserDto> findUsersOlderThan(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return userService.findUsersOlderThan(localDate)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

}
