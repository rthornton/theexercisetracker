package com.theexercisetracker.tcx;

import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

/**
 * Created by rob on 3/17/14.
 */
public interface TcxParser {
    TrainingCenterDatabaseT parse(InputStream is) throws JAXBException;
}
