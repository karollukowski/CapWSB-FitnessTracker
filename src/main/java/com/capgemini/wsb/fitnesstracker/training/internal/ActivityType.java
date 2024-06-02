package com.capgemini.wsb.fitnesstracker.training.internal;

// TODO : JavaDoc
/**
 * Enum representing the type of activity.
 * Running, Cycling, Walking, Swimming, Tennis.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    /**
     * Konstrukcja nowego typu aktywności
     * @param displayName nazwa aktywności
     */

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
