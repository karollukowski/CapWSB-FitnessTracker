package com.capgemini.wsb.fitnesstracker.mail.api;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingCompletionEvent;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingCompletionRequest;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails.
 */
@Service
public class EmailService {
    private final EmailSender emailSender;

    /**
     * Creates an instance of the service.
     * @param emailSender email sender
     */
    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Sends an email with information about training completion.
     * @param request training information
     */
    public void sendTrainingCompletionEmail(TrainingCompletionRequest request) {

        String toAddress = request.getUserEmail();
        String subject = "Training Completed";
        String content = "Activity: " + request.getActivityName() + ", Duration: " + request.getActivityDuration() + ", Message: " + request.getAdditionalText();

        EmailDto email = new EmailDto(toAddress, subject, content);
        emailSender.send(email);
    }
}
