package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    @Autowired

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Pobiera wszystkich użytkowników
     * @return lista użytkowników
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Dodaje użytkownika
     * @param userDto dane użytkownika
     * @return dodany użytkownik
     * @throws InterruptedException
     */
    @PostMapping
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // TODO: saveUser with Service and return User
        User user = new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());

        return userService.createUser(user);
    }

    /**
     * Usuwa użytkownika
     * @param id id użytkownika
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Aktualizuje użytkownika
     * @param userDto dane użytkownika
     * @return zaktualizowany użytkownik
     */
    @PutMapping
    public User updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userService.updateUser(user);
    }

    /**
     * Wyszukuje użytkowników po adresie e-mail
     * @param email adres e-mail
     * @return lista użytkowników
     */
    @GetMapping("/search/email")
    public List<UserDto> findUsersByEmail(@RequestParam String email) {
        return userService.findUsersByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Wyszukuje użytkowników po fragmencie imienia lub nazwiska
     * @param nameFragment fragment imienia lub nazwiska
     * @return lista użytkowników
     */
    @GetMapping("/search/name")
    public List<UserDto> findUsersByNameFragment(@RequestParam String nameFragment) {
        return userService.findUsersByNameFragment(nameFragment)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Wyszukuje użytkowników starszych niż podana data
     * @param date data
     * @return lista użytkowników
     */
    @GetMapping("/search/age")
    public List<UserDto> findUsersOlderThan(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return userService.findUsersOlderThan(localDate)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Pobiera użytkownika po id
     * @param id id użytkownika
     * @throws ResponseStatusException jeśli użytkownik nie istnieje
     * @return użytkownik
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.getUser(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toDto(user);
    }

    /**
     * Pobiera użytkownika po adresie e-mail
     * @param email adres e-mail
     * @throws ResponseStatusException jeśli użytkownik nie istnieje
     * @return użytkownik
     */
    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toDto(user);
    }

}
