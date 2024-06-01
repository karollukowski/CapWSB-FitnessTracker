package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailService;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingCompletionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler odpowiedzialny za obsługę żądań związanych z wysyłaniem training completion maili
 */

@RestController
@RequestMapping("/api/training-emails")
public class TrainingEmailController {

    private final EmailService emailService;

    /**
     * Konstrukcja nowej instancji TrainingEmailController
     * @param emailService odpowiedzialny za wysyłanie maili
     */

    public TrainingEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Wysyła maila z informacją o zakończeniu treningu
     * @param request informacje o treningu
     * @return ResponseEntity
     */

    @PostMapping
    public ResponseEntity<Void> sendTrainingCompletionEmail(@RequestBody TrainingCompletionRequest request) {
        emailService.sendTrainingCompletionEmail(request);
        return ResponseEntity.ok().build();
    }
}
