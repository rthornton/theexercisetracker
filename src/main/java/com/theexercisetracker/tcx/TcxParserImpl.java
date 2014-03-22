package com.theexercisetracker.tcx;

import com.garmin.xmlschemas.trainingcenterdatabase.v2.ActivityLapT;
import com.garmin.xmlschemas.trainingcenterdatabase.v2.ActivityT;
import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TcxParserImpl implements TcxParser {

    @Override
    public TrainingCenterDatabaseT initialize(InputStream is) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TrainingCenterDatabaseT.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Source source = new StreamSource(is);
        JAXBElement<TrainingCenterDatabaseT> root = unmarshaller.unmarshal(source, TrainingCenterDatabaseT.class);
        return root.getValue();
    }

    @Override
    public List<ActivityT> loadRunningActivities(TrainingCenterDatabaseT db) {
        List<ActivityT> activities = db.getActivities().getActivity();
        List<ActivityT> runningActivities = new ArrayList<ActivityT>();
        for (ActivityT activity : activities) {
            if (activity.getSport().value().equalsIgnoreCase("Running")) {
                runningActivities.add(activity);
            }
        }
        return runningActivities;
    }

    @Override
    public double getDistanceInMeters(ActivityT runningActivity) {
        List<ActivityLapT> laps = runningActivity.getLap();
        double distance = 0;
        for (ActivityLapT lap : laps) {
            distance += lap.getDistanceMeters();
        }
        return distance;
    }

    @Override
    public double getTotalTimeInSeconds(ActivityT runningActivity) {
        List<ActivityLapT> laps = runningActivity.getLap();
        double timeInSeconds = 0;
        for (ActivityLapT lap : laps) {
            timeInSeconds += lap.getTotalTimeSeconds();
        }
        return timeInSeconds;
    }

    @Override
    public int getTotalCaloriesBurned(ActivityT activity) {
        List<ActivityLapT> laps = activity.getLap();
        int calories = 0;
        for (ActivityLapT lap : laps) {
            calories += lap.getCalories();
        }
        return calories;
    }

    @Override
    public Tcx loadCoreValues(InputStream tcxFile, ActivityTypes activityType) throws JAXBException {
        TrainingCenterDatabaseT db = initialize(tcxFile);
        if (activityType == ActivityTypes.RUNNING) {
            List<ActivityT> runningActivities = loadRunningActivities(db);
            assert (runningActivities.size() == 1);
            Tcx coreValues = new Tcx();
            coreValues.setDistanceInMeters(getDistanceInMeters(runningActivities.get(0)));
            coreValues.setTotalTimeInSeconds(getTotalTimeInSeconds(runningActivities.get(0)));
            coreValues.setTotalCaloriesBurned(getTotalCaloriesBurned(runningActivities.get(0)));
            return coreValues;
        } else {
            throw new UnsupportedOperationException("Bad Activity Type");
        }
    }
}
