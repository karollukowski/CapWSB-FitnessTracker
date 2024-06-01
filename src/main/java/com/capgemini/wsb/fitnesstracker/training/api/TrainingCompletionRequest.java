package com.capgemini.wsb.fitnesstracker.training.api;

public class TrainingCompletionRequest {

    private Long userId;
    private String userEmail;
    private String activityName;
    private double activityDuration;
    private String additionalText;

    public Long getUserId() {
        return userId;
    }

    public String getActivityName() {
        return activityName;
    }

    public double getActivityDuration() {
        return activityDuration;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityDuration(double activityDuration) {
        this.activityDuration = activityDuration;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
