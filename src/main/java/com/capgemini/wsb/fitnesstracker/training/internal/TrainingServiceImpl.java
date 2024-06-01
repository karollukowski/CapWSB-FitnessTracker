package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingCompletionEvent;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingCompletionRequest;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// TODO: Provide Impl

/**
 * Service implementation for training management.

 */
@Service
public class TrainingServiceImpl implements TrainingProvider {

    private final ApplicationEventPublisher eventPublisher;

    public TrainingServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingMapper trainingMapper;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public Training createTraining(TrainingDto trainingDto) {
        User user = userRepository.findById(trainingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Training training = trainingMapper.toEntity(trainingDto, user);
        return trainingRepository.save(training);
    }

    /**
     * Retrieves all trainings.
     *
     * @return List of all trainings
     */

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUser(Long userId) {
        return trainingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Training> getCompletedTrainings(Date date) {
        return trainingRepository.findAll().stream()
                .filter(training -> training.getEndTime().after(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Training> getTrainingsByActivity(ActivityType activityType) {
        return trainingRepository.findAllByActivityType(activityType);
    }

    @Override
    public Training updateTraining(Long trainingId, TrainingDto trainingDto) {
        Training existingTraining = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));
        User user = userRepository.findById(trainingDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(trainingDto.getUserId()));
        Training updatedTraining = trainingMapper.updateEntityFromDto(trainingDto, user);
        updatedTraining = trainingRepository.save(updatedTraining);

        TrainingCompletionRequest request = new TrainingCompletionRequest();
        request.setUserId(updatedTraining.getUser().getId());
        request.setUserEmail(updatedTraining.getUser().getEmail());
        request.setActivityName(updatedTraining.getActivityType().name());
        request.setActivityDuration(updatedTraining.getEndTime().getTime() - updatedTraining.getStartTime().getTime());
        request.setAdditionalText("Training completed successfully");
        eventPublisher.publishEvent(new TrainingCompletionEvent(this, request));

        return updatedTraining;
    }
    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */

}
