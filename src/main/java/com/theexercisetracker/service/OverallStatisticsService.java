package com.theexercisetracker.service;

import com.theexercisetracker.persistence.model.Activity;

import java.util.List;

public class OverallStatisticsService {
    public long calculateTotalDistance(List<Activity> activities) {
        long total = 0;
        for (Activity a : activities) {
            total += a.getDistanceInMeters();
        }
        return total;
    }


    public long calculateTotalCalories(List<Activity> activities) {
        long total = 0;
        for (Activity a : activities) {
            total += a.getTotalCaloriesBurned();
        }
        return total;
    }
}
