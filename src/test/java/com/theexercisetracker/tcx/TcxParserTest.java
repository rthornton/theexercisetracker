package com.theexercisetracker.tcx;


import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TcxParserTest {

    @Test
    public void serviceParsesFile() throws FileNotFoundException, JAXBException {
        TcxParser parser = new TcxParserImpl();
        InputStream is = new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx");
        TrainingCenterDatabaseT parsedFile = parser.parse(is);
        Assert.assertNotNull(parsedFile);
    }

    @Test
    public void parseXml() throws FileNotFoundException, JAXBException {
        TcxParser parser = new TcxParserImpl();
        InputStream is = new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx");
        TrainingCenterDatabaseT parsedFile = parser.parse(is);
        Assert.assertNotNull(parsedFile);

    }
}
