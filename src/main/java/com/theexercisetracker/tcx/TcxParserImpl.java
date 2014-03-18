package com.theexercisetracker.tcx;

import java.io.InputStream;

/**
 * Created by rob on 3/17/14.
 */
public class TcxParserImpl implements TcxParser {
    @Override
    public Tcx parse(InputStream is) {
        return new Tcx();
    }
}
