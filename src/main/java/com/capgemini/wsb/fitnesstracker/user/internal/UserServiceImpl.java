package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(final User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findUsersByEmail(final String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> findUsersByNameFragment(final String nameFragment) {
        return userRepository.findByFirstNameContainingIgnoreCase(nameFragment);
    }

    @Override
    public List<User> findUsersOlderThan(final LocalDate date) {
        return userRepository.findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .collect(Collectors.toList());
    }

}
