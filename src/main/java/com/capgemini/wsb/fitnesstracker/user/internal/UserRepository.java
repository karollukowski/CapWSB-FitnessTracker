package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Repository for managing {@link User} entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by ignoring case.
     *
     * @param email email of the user to search
     * @return list of users with matching email
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    List<User> findByEmailIgnoreCase(@Param("email") String email);

    /**
     * Query searching users by first name. It matches by ignoring case.
     *
     * @param nameFragment fragment of the first name to search
     * @return list of users with matching first name
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :nameFragment, '%'))")
    List<User> findByFirstNameContainingIgnoreCase(@Param("nameFragment") String nameFragment);

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

}
