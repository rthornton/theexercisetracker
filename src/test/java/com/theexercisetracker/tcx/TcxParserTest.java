package com.theexercisetracker.tcx;


import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TcxParserTest {

    @Test
    public void serviceParsesFile() throws FileNotFoundException {
        TcxParser parser = new TcxParserImpl();
        InputStream is = new FileInputStream("com/theexercisetracker/tcx/2014-03-17T05_51_38-400_Running.tcx");
        Tcx parsedFile = parser.parse(is);
        Assert.assertNotNull(parsedFile);
    }
}
