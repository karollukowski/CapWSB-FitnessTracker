package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for managing {@link Training}.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves all trainings for a given user.
     *
     * @param userId the ID of the user
     * @return List of all trainings for the user
     */
    List<Training> findAllByUserId(Long userId);

    /**
     * Retrieves all trainings of a given activity type.
     *
     * @param activityType the type of the activity
     * @return List of all trainings of the given activity type
     */
    List<Training> findAllByActivityType(ActivityType activityType);
}

