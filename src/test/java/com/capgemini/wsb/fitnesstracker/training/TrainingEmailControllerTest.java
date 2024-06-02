package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailService;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingCompletionRequest;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingEmailController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * Unit tests for TrainingEmailController
 */
public class TrainingEmailControllerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private TrainingEmailController trainingEmailController;

    private MockMvc mockMvc;

    /**
     * Set up mocks
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainingEmailController).build();
    }

    /**
     * Test that a training completion email can be sent
     * @throws Exception
     */
    @Test
    public void shouldSendTrainingCompletionEmail() throws Exception {
        doNothing().when(emailService).sendTrainingCompletionEmail(any(TrainingCompletionRequest.class));

        mockMvc.perform(post("/api/training-emails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"userEmail\":\"Emma.Johnson@domain.com\",\"activityName\":\"Running\",\"activityDuration\":30,\"additionalText\":\"Świetnie!\"}"))
                .andExpect(status().isOk());

        verify(emailService, times(1)).sendTrainingCompletionEmail(any(TrainingCompletionRequest.class));
    }

    /**
     * Test that a bad request is returned when the request body is missing
     * @throws Exception
     */
    @Test
    public void shouldReturnBadRequestWhenRequestBodyIsMissing() throws Exception {
        mockMvc.perform(post("/api/training-emails")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(emailService, times(0)).sendTrainingCompletionEmail(any(TrainingCompletionRequest.class));
    }

    /**
     * Test that returns ok when email is successfully sent
     * @throws Exception
     */
    @Test
    public void shouldReturnOkWhenEmailIsSuccessfullySent() throws Exception {
        doNothing().when(emailService).sendTrainingCompletionEmail(any(TrainingCompletionRequest.class));

        mockMvc.perform(post("/api/training-emails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"userEmail\":\"Emma.Johnson@domain.com\",\"activityName\":\"Running\",\"activityDuration\":30,\"additionalText\":\"Świetnie!\"}"))
                .andExpect(status().isOk());

        verify(emailService, times(1)).sendTrainingCompletionEmail(any(TrainingCompletionRequest.class));
    }

}