package com.capgemini.wsb.fitnesstracker.training.api;

/**
 * Request containing information about completed training.
 */
public class TrainingCompletionRequest {

    private Long userId;
    private String userEmail;
    private String activityName;
    private double activityDuration;
    private String additionalText;


    /**
     * Returns the ID of the user who completed the training.
     * @return the ID of the user who completed the training
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the name of the activity that was completed.
     * @return the name of the activity that was completed
     */

    public String getActivityName() {
        return activityName;
    }

    /**
     * Returns the duration of the activity that was completed.
     * @return the duration of the activity that was completed
     */
    public double getActivityDuration() {
        return activityDuration;
    }

    /**
     * Returns additional information about the completed training.
     * @return additional information about the completed training
     */
    public String getAdditionalText() {
        return additionalText;
    }

    /**
     * Sets the ID of the user who completed the training.
     * @param userId the ID of the user who completed the training
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Sets the name of the activity that was completed.
     * @param activityName the name of the activity that was completed
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * Sets the duration of the activity that was completed.
     * @param activityDuration the duration of the activity that was completed
     */
    public void setActivityDuration(double activityDuration) {
        this.activityDuration = activityDuration;
    }

    /**
     * Sets additional information about the completed training.
     * @param additionalText additional information about the completed training
     */
    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }

    /**
     * Returns the email address of the user who completed the training.
     * @return the email address of the user who completed the training
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the email address of the user who completed the training.
     * @param userEmail the email address of the user who completed the training
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
