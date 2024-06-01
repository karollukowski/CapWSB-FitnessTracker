package com.capgemini.wsb.fitnesstracker.training.api;

import org.springframework.context.ApplicationEvent;

/**
 * Event indicating that the training has been completed.
 */
public class TrainingCompletionEvent extends ApplicationEvent {
    private final TrainingCompletionRequest request;

    /**
     * Constructs a new instance of TrainingCompletionEvent
     * @param source the object on which the event initially occurred
     * @param request training information
     */

    public TrainingCompletionEvent(Object source, TrainingCompletionRequest request) {
        super(source);
        this.request = request;
    }

    /**
     * Returns request treningu zawierający informacje o uokończonym treningu
     * @return the request containing training information
     */

    public TrainingCompletionRequest getRequest() {
        return request;
    }
}