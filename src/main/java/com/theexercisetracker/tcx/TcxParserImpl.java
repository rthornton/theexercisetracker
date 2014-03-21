package com.theexercisetracker.tcx;

import com.garmin.xmlschemas.trainingcenterdatabase.v2.TrainingCenterDatabaseT;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * Created by rob on 3/17/14.
 */
public class TcxParserImpl implements TcxParser {
    @Override
    public TrainingCenterDatabaseT parse(InputStream is) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TrainingCenterDatabaseT.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
//        return (TrainingCenterDatabaseT) unmarshaller.unmarshal(is);
//
        Source source = new StreamSource(is);
        JAXBElement<TrainingCenterDatabaseT> root = unmarshaller.unmarshal(source, TrainingCenterDatabaseT.class);
        return root.getValue();
    }
}
