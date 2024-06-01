package com.capgemini.wsb.fitnesstracker.training.api;

import org.springframework.context.ApplicationEvent;

public class TrainingCompletionEvent extends ApplicationEvent {
    private final TrainingCompletionRequest request;

    public TrainingCompletionEvent(Object source, TrainingCompletionRequest request) {
        super(source);
        this.request = request;
    }

    public TrainingCompletionRequest getRequest() {
        return request;
    }
}