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

    /**
     * Deletes a user based on their ID.
     *
     * @param userId id of the user to be deleted
     */
    @Override
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Updates a user.
     *
     * @param user the user to be updated
     */
    @Override
    public User updateUser(final User user) {
        return userRepository.save(user);
    }

    /**
     * Finds users by email.
     *
     * @param email the email to search for
     */
    @Override
    public List<User> findUsersByEmail(final String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    /**
     * Finds users by name fragment.
     *
     * @param nameFragment the name fragment to search for
     */
    @Override
    public List<User> findUsersByNameFragment(final String nameFragment) {
        return userRepository.findByFirstNameContainingIgnoreCase(nameFragment);
    }

    /**
     * Finds users older than a given date.
     *
     * @param date the date to compare
     */
    @Override
    public List<User> findUsersOlderThan(final LocalDate date) {
        return userRepository.findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .collect(Collectors.toList());
    }

}
