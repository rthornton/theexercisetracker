package com.theexercisetracker.tcx;

import java.io.InputStream;

/**
 * Created by rob on 3/17/14.
 */
public interface TcxParser {
    Tcx parse(InputStream is);
}
