package com.theexercisetracker.service;


import com.theexercisetracker.persistence.model.Activity;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Test
    public void groupActivitiesByYear() {
        List<Activity> activities = generateWeeklyActivities();
        Map<Integer, List<Activity>> groupActivitiesByYear = new OverallStatisticsService().groupActivitiesByYear(activities);
        Assert.assertEquals(2, groupActivitiesByYear.keySet().size());
        Assert.assertEquals(1, groupActivitiesByYear.get(2013).size());
        Assert.assertEquals(new Integer(5), groupActivitiesByYear.get(2013).get(0).getId());
        Assert.assertEquals(5, groupActivitiesByYear.get(2014).size());
    }

    @Test
    public void groupActivitiesByWeek() {
        List<Activity> activities = generateWeeklyActivities();
        List<List<Activity>> activitiesByWeek = new OverallStatisticsService().groupActivitiesByWeek(activities);
        Assert.assertEquals(4, activitiesByWeek.size());
        Assert.assertEquals(1, activitiesByWeek.get(0).size());
        Assert.assertEquals(new Integer(5), activitiesByWeek.get(0).get(0).getId());
        Assert.assertEquals(2, activitiesByWeek.get(1).size());
        Assert.assertEquals(1, activitiesByWeek.get(2).size());
        Assert.assertEquals(2, activitiesByWeek.get(3).size());
    }

    /*
      W1:  Multiple per week, crossing year & month boundary
        - A1
        - A4
        - A5
      W2:  Single
        - A2
      W3:  Multiple per week, no boundary crossing
        - A3
        - A6
     */
    private List<Activity> generateWeeklyActivities() {
        List<Activity> activities = new ArrayList<>();

        Activity a = new Activity();
        a.setStartTime(ZonedDateTime.of(2014, 1, 1, 1, 1, 1, 1, ZoneId.systemDefault()));
        a.setDistanceInMeters(1000);
        a.setTotalCaloriesBurned(500);
        a.setId(1);
        activities.add(a);

        a = new Activity();
        a.setStartTime(ZonedDateTime.of(2014, 1, 5, 1, 1, 1, 1, ZoneId.systemDefault()));
        a.setDistanceInMeters(2000);
        a.setTotalCaloriesBurned(1500);
        a.setId(2);
        activities.add(a);

        a = new Activity();
        a.setStartTime(ZonedDateTime.of(2014, 2, 3, 1, 1, 1, 1, ZoneId.systemDefault()));
        a.setDistanceInMeters(2000);
        a.setTotalCaloriesBurned(1500);
        a.setId(3);
        activities.add(a);

        a = new Activity();
        a.setStartTime(ZonedDateTime.of(2014, 1, 3, 1, 1, 1, 1, ZoneId.systemDefault()));
        a.setDistanceInMeters(2000);
        a.setTotalCaloriesBurned(1500);
        a.setId(4);
        activities.add(a);

        a = new Activity();
        a.setStartTime(ZonedDateTime.of(2013, 12, 31, 1, 1, 1, 1, ZoneId.systemDefault()));
        a.setDistanceInMeters(2000);
        a.setTotalCaloriesBurned(1500);
        a.setId(5);
        activities.add(a);

        a = new Activity();
        a.setStartTime(ZonedDateTime.of(2014, 2, 5, 1, 1, 1, 1, ZoneId.systemDefault()));
        a.setDistanceInMeters(2000);
        a.setTotalCaloriesBurned(1500);
        a.setId(6);
        activities.add(a);


        return activities;
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
