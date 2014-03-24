package com.theexercisetracker.tcx;

import com.garmin.xmlschemas.trainingcenterdatabase.v2.ActivityT;
import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;
import com.theexercisetracker.persistence.Activity;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.List;

public interface TcxParser {

    enum ActivityTypes {
        RUNNING
    }

    TrainingCenterDatabaseT initialize(InputStream is) throws JAXBException;

    List<ActivityT> loadRunningActivities(TrainingCenterDatabaseT db);

    double getDistanceInMeters(ActivityT activity);

    double getTotalTimeInSeconds(ActivityT activity);

    int getTotalCaloriesBurned(ActivityT activity);

    Activity loadCoreValues(InputStream tcxFile, ActivityTypes activityType) throws JAXBException;
}
