package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.training.internal.*;
import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.*;
import com.capgemini.wsb.fitnesstracker.user.internal.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrainingServiceImplTest {


    @Mock
    TrainingRepository trainingRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    TrainingMapper trainingMapper;

    @InjectMocks
    TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTraining() {
        TrainingDto trainingDto = new TrainingDto();
        User user = new User("Emma", "Johnson", LocalDate.now(), "emma.johnson@domain.com");
        Training training = new Training(user, new Date(), new Date(), ActivityType.RUNNING, 4.0, 3.5);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(trainingMapper.toEntity(any(TrainingDto.class), any(User.class))).thenReturn(training);
        when(trainingRepository.save(any(Training.class))).thenReturn(training);

        Training result = trainingService.createTraining(trainingDto);

        assertNotNull(result);
        assertEquals(training, result);
    }

    @Test
    void shouldUpdateTraining() {
        TrainingDto trainingDto = new TrainingDto();
        User user = new User("Emma", "Johnson", LocalDate.now(), "emma.johnson@domain.com");
        Training existingTraining = new Training(user, new Date(), new Date(), ActivityType.RUNNING, 9.0, 5.0);
        Training updatedTraining = new Training(user, new Date(), new Date(), ActivityType.RUNNING, 12.0, 7.5);

        when(trainingRepository.findById(anyLong())).thenReturn(Optional.of(existingTraining));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(trainingMapper.updateEntityFromDto(any(TrainingDto.class), any(User.class))).thenReturn(updatedTraining);
        when(trainingRepository.save(any(Training.class))).thenReturn(updatedTraining);

        Training result = trainingService.updateTraining(1L, trainingDto);

        assertNotNull(result);
        assertEquals(updatedTraining, result);
    }

    @Test
    void shouldGetAllTrainings() {
        Training training = new Training(new User("Emma", "Johnson", LocalDate.now(), "emma.johnson@domain.com"), new Date(), new Date(), ActivityType.RUNNING, 10.0, 5.0);
        List<Training> trainings = Collections.singletonList(training);

        when(trainingRepository.findAll()).thenReturn(trainings);

        List<Training> result = trainingService.getAllTrainings();

        assertNotNull(result);
        assertEquals(trainings, result);
    }

}
