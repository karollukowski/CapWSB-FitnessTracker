package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Data transfer object for training.
 */
public class TrainingDto {
    private Long id;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

    /**
     * Sets the ID of the training.
     * @param id the ID of the training
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the ID of the user who completed the training.
     * @param userId the ID of the user who completed the training
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Sets the start time of the training.
     * @param startTime the start time of the training
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the end time of the training.
     * @param endTime the end time of the training
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets the type of the activity that was completed.
     * @param activityType the type of the activity that was completed
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * Sets the distance covered during the training.
     * @param distance the distance covered during the training
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Sets the average speed during the training.
     * @param averageSpeed the average speed during the training
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * Returns the ID of the training.
     * @return the ID of the training
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the ID of the user who completed the training.
     * @return the ID of the user who completed the training
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the start time of the training.
     * @return the start time of the training
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the training.
     * @return the end time of the training
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Returns the type of the activity that was completed.
     * @return the type of the activity that was completed
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Returns the distance covered during the training.
     * @return the distance covered during the training
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the average speed during the training.
     * @return the average speed during the training
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }
}
