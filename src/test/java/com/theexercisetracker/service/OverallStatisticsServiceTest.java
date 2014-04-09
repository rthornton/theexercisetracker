package com.theexercisetracker.service;


import com.theexercisetracker.persistence.model.Activity;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OverallStatisticsServiceTest {

    @Test
    public void calculateOverallDistanceForActivities() {
        List<Activity> activities = generateActivities();
        Assert.assertEquals(3000, new OverallStatisticsService().calculateTotalDistance(activities));
    }

    @Test
    public void calculateCaloriesForActivities() {
        List<Activity> activities = generateActivities();
        Assert.assertEquals(2000, new OverallStatisticsService().calculateTotalCalories(activities));
    }

    /**
     * Sort by date
     * Want JPA query to return sorted by date
     *
     */

    @Test
    public void calculateDistanceByWeek() {
        List<Activity> activities = generateActivities();
//        Map<>
        Assert.assertEquals(2000, new OverallStatisticsService().calculateTotalCalories(activities));
    }

    private List<Activity> generateActivities() {
        List<Activity> activities = new ArrayList<Activity>();

        Activity a = new Activity();
        a.setDistanceInMeters(1000);
        a.setTotalCaloriesBurned(500);
        activities.add(a);

        a = new Activity();
        a.setDistanceInMeters(2000);
        a.setTotalCaloriesBurned(1500);
        activities.add(a);

        return activities;
    }
}
