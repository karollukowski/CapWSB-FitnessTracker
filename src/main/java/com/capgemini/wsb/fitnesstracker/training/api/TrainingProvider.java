package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    /**
     * Creates a new training.
     *
     * @param trainingDto the training to be created
     * @return the created training
     */
    Training createTraining(TrainingDto trainingDto);

    /**
     * Retrieves all trainings.
     *
     * @return List of all trainings
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all trainings for a given user.
     *
     * @param userId the ID of the user
     * @return List of all trainings for the user
     */
    List<Training> getTrainingsByUser(Long userId);

    /**
     * Retrieves all trainings completed on a given date.
     *
     * @param date the date after which the trainings were completed
     * @return List of all trainings completed on the given date
     */

    List<Training> getCompletedTrainings(Date date);

    /**
     * Retrieves all trainings of a given activity type.
     *
     * @param activityType the type of the activity
     * @return List of all trainings of the given activity type
     */
    List<Training> getTrainingsByActivity(ActivityType activityType);

    /**
     * Updates a training.
     *
     * @param trainingId the ID of the training to be updated
     * @param trainingDto the updated training
     * @return the updated training
     */
    Training updateTraining(Long trainingId, TrainingDto trainingDto);
}
