package com.theexercisetracker.tcx;


import com.garmin.xmlschemas.trainingcenterdatabase.v2.ActivityT;
import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;
import com.theexercisetracker.persistence.model.Activity;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class TcxParserTest {

    @Test
    public void serviceParsesFile() throws FileNotFoundException, JAXBException {
        TcxParser parser = new TcxParserImpl();
        InputStream is = new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx");
        TrainingCenterDatabaseT parsedFile = parser.initialize(is);
        Assert.assertNotNull(parsedFile);
    }

    @Test
    public void parseFindRunningActivity() throws FileNotFoundException, JAXBException {
        TcxParser parser = new TcxParserImpl();
        TrainingCenterDatabaseT parsedFile = parser.initialize(new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx"));
        List<ActivityT> activities = parsedFile.getActivities().getActivity();
        List<ActivityT> runningActivities = new ArrayList<ActivityT>();
        for (ActivityT activity : activities) {
            if (activity.getSport().value().equalsIgnoreCase("Running")) {
                runningActivities.add(activity);
            }
        }
        Assert.assertEquals(1, runningActivities.size());
    }

    @Test
    public void loadAllRunningActivities() throws FileNotFoundException, JAXBException {
        TcxParserImpl tcxParser = new TcxParserImpl();
        TrainingCenterDatabaseT parsedFile = tcxParser.initialize(new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx"));
        List<ActivityT> runningActivities = tcxParser.loadRunningActivities(parsedFile);
        Assert.assertEquals(1, runningActivities.size());
    }

    @Test
    public void getDistanceInMetersForRunningActivity() throws FileNotFoundException, JAXBException {
        TcxParserImpl tcxParser = new TcxParserImpl();
        TrainingCenterDatabaseT parsedFile = tcxParser.initialize(new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx"));
        ActivityT runningActivity = tcxParser.loadRunningActivities(parsedFile).get(0);
        double distance = tcxParser.getDistanceInMeters(runningActivity);
        Assert.assertEquals(11842.124878, distance, 0.0000001);
    }

    @Test
    public void getTotalTimeInSeconds() throws FileNotFoundException, JAXBException {
        TcxParserImpl tcxParser = new TcxParserImpl();
        TrainingCenterDatabaseT parsedFile = tcxParser.initialize(new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx"));
        ActivityT activity = tcxParser.loadRunningActivities(parsedFile).get(0);
        double timeInSeconds = tcxParser.getTotalTimeInSeconds(activity);
        Assert.assertEquals(4194, timeInSeconds, 0.01);
    }

    @Test
    public void getTotalCaloriesBurned() throws FileNotFoundException, JAXBException {
        TcxParserImpl tcxParser = new TcxParserImpl();
        TrainingCenterDatabaseT parsedFile = tcxParser.initialize(new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx"));
        ActivityT runningActivity = tcxParser.loadRunningActivities(parsedFile).get(0);
        int calories = tcxParser.getTotalCaloriesBurned(runningActivity);
        Assert.assertEquals(1039, calories);
    }

    @Test
    public void loadCoreDataValues() throws FileNotFoundException, JAXBException {
        TcxParserImpl tcxParser = new TcxParserImpl();
        Activity coreValues = tcxParser.loadCoreValues(new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx"), TcxParser.ActivityTypes.RUNNING);
        Assert.assertEquals(1039, coreValues.getTotalCaloriesBurned());
        Assert.assertEquals(4194, coreValues.getTotalTimeInSeconds(), 0.01);
        Assert.assertEquals(11842.124878, coreValues.getDistanceInMeters(), 0.0000001);
        Assert.assertEquals("2014-03-17T09:51:38Z", coreValues.getIdAsString());
    }

    @Test
    public void understandHowTheDateIdIsDone() throws FileNotFoundException, JAXBException {
        TcxParserImpl tcxParser = new TcxParserImpl();
        TrainingCenterDatabaseT parsedFile = tcxParser.initialize(new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx"));
        XMLGregorianCalendar id = parsedFile.getActivities().getActivity().get(0).getId();
        System.out.println("id.toGregorianCalendar().toString() = " + id.toGregorianCalendar().toString());
        ZonedDateTime zonedDateTime = id.toGregorianCalendar().toZonedDateTime();
        System.out.println("zonedDateTime.toString() = " + zonedDateTime.toString());
        System.out.println("zonedDateTime = " + zonedDateTime.getZone().toString());
        System.out.println(zonedDateTime.toLocalDateTime().toString());
        System.out.println("zonedDateTime = " + zonedDateTime.withZoneSameLocal(ZoneId.of("America/New_York")));
        System.out.println("zonedDateTime = " + zonedDateTime.withZoneSameInstant(ZoneId.of("America/New_York")));
    }

//    <Activities>
//    <Activity Sport="Running">
//    <Notes>Legs still a bit tired from Saturday. Light dusting of snow. Chilly.</Notes>
//    <Id>2014-03-17T09:51:38Z</Id>
//    <Lap StartTime="2014-03-17T09:51:38Z">
//    <TotalTimeSeconds>594</TotalTimeSeconds>
//    <DistanceMeters>1609.267700</DistanceMeters>
//    <Calories>141</Calories>
//    <Intensity>Active</Intensity>
//    <TriggerMethod>Manual</TriggerMethod>
}
