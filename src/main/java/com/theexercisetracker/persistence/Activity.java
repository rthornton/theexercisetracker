package com.theexercisetracker.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Activity {

    @Id
    @GeneratedValue
    private Integer id;

    private double distanceInMeters;
    private double totalTimeInSeconds;
    private int totalCaloriesBurned;

    public void setDistanceInMeters(double distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public double getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setTotalTimeInSeconds(double totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    public double getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public void setTotalCaloriesBurned(int totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public int getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }
}
