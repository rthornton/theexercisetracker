package com.theexercisetracker.service;

import com.theexercisetracker.persistence.model.Activity;

import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.*;

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

    /**
     * Group activities by year/month
     * @return Map of week --> List of Activities
     * @param activities
     */
    public List<List<Activity>> groupActivitiesByWeek(List<Activity> activities) {
        Map<Integer, List<Activity>> activitiesByYear = groupActivitiesByYear(activities);

        List<List<Activity>> activitiesByWeek = new ArrayList<>();
        for (Integer year : activitiesByYear.keySet()) {
            Map<Integer, List<Activity>> activitiesByWeekOfYear = new TreeMap<>();

            for (Activity activity : activitiesByYear.get(year)) {
                ZonedDateTime startTime = activity.getStartTime();
                if (!activitiesByWeekOfYear.containsKey(startTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR))) {
                    activitiesByWeekOfYear.put(startTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR), new ArrayList<>());
                }

                activitiesByWeekOfYear.get(startTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR)).add(activity);
            }

            for(Integer week : activitiesByWeekOfYear.keySet()) {
                activitiesByWeek.add(activitiesByWeekOfYear.get(week));
            }
        }

        return activitiesByWeek;
    }

    public Map<Integer, List<Activity>> groupActivitiesByYear(List<Activity> activities) {
        Map<Integer, List<Activity>> activitiesByYear = new TreeMap<>();
        for (Activity activity : activities) {
            ZonedDateTime startTime = activity.getStartTime();
            if (!activitiesByYear.containsKey(startTime.getYear())) {
                activitiesByYear.put(startTime.getYear(), new ArrayList<>());
            }

            activitiesByYear.get(startTime.getYear()).add(activity);
        }
        return activitiesByYear;
    }
}
