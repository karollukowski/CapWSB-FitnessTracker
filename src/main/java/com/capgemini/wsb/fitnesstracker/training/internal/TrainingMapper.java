package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import org.springframework.stereotype.Component;


/**
 * Mapper for converting between {@link Training} and {@link TrainingDto}.
 */
@Component
public class TrainingMapper {

    /**
     * Converts a {@link Training} to a {@link TrainingDto}.
     *
     * @param training the training to be converted
     * @return the converted training
     */

    public TrainingDto toDto(Training training) {
        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setUserId(training.getUser().getId());
        dto.setStartTime(training.getStartTime());
        dto.setEndTime(training.getEndTime());
        dto.setActivityType(training.getActivityType());
        dto.setDistance(training.getDistance());
        dto.setAverageSpeed(training.getAverageSpeed());
        return dto;
    }

    /**
     * Converts a {@link TrainingDto} to a {@link Training}.
     *
     * @param dto the training to be converted
     * @param user the user to be associated with the training
     * @return the converted training entity
     */

    public Training toEntity(TrainingDto dto, User user) {
        Training training = new Training(
                user,
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getActivityType(),
                dto.getDistance(),
                dto.getAverageSpeed()
        );
        return training;
    }

    /**
     * Updates a {@link Training} entity with data from a {@link TrainingDto}.
     *
     * @param dto the training data
     * @param user the user to be associated with the training
     * @return the updated training entity
     */

    public Training updateEntityFromDto(TrainingDto dto, User user) {
        return new Training(
                user,
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getActivityType(),
                dto.getDistance(),
                dto.getAverageSpeed()
        );
    }
}