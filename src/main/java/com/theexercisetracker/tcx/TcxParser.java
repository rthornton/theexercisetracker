package com.theexercisetracker.tcx;

import com.garmin.xmlschemas.trainingcenterdatabase.v2.ActivityT;
import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by rob on 3/17/14.
 */
public interface TcxParser {
    TrainingCenterDatabaseT initialize(InputStream is) throws JAXBException;

    List<ActivityT> loadRunningActivities(TrainingCenterDatabaseT db);

    double getDistanceInMeters(ActivityT runningActivity);

    double getTotalTimeInSeconds(ActivityT runningActivity);

    int getTotalCaloriesBurned(ActivityT activity);

    }
