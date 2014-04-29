package com.theexercisetracker.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class Activity {

    @Id
    @GeneratedValue
    private Integer id;

    private double distanceInMeters;
    private double totalTimeInSeconds;
    private int totalCaloriesBurned;
    private String idAsString;
    private ZonedDateTime startTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdAsString() {
        return idAsString;
    }

    public void setIdAsString(String idAsString) {
        this.idAsString = idAsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (id != null ? !id.equals(activity.id) : activity.id != null) return false;
        if (idAsString != null ? !idAsString.equals(activity.idAsString) : activity.idAsString != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idAsString != null ? idAsString.hashCode() : 0);
        return result;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }
}
