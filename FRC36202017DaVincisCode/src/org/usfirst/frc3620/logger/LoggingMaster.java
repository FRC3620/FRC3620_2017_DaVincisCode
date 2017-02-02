package org.usfirst.frc3620.logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class LoggingMaster {
    private final static long SOME_TIME_AFTER_1970 = 523980000000L;

    private static String _timestampString = null;

    private static File _logDirectory = null;

    // http://javarevisited.blogspot.com/2014/05/double-checked-locking-on-singleton-in-java.html
    public static String getTimestampString() {
        if (_timestampString == null) { // do a quick check (no overhead from
                                        // synchonized)
            synchronized (LoggingMaster.class) {
                if (_timestampString == null) { // Double checked
                    long now = System.currentTimeMillis();
                    
                    if (now > SOME_TIME_AFTER_1970) {
                        SimpleDateFormat formatName = new SimpleDateFormat(
                                "yyyyMMdd-HHmmss");
                        _timestampString = formatName.format(new Date());
                        String logMessage = String.format(
                                "timestamp for logs is %s\n", _timestampString);
                        EventLogging.writeToDS(logMessage);
                    }
                }
            }
        }
        return _timestampString;
    }

    public static File getLoggingDirectory() {
        if (_logDirectory == null) { // quick check
            synchronized (LoggingMaster.class) {
                if (_logDirectory == null) {
                    // Set dataLogger and Time information
                    TimeZone.setDefault(
                            TimeZone.getTimeZone("America/Detroit"));

                    if (_logDirectory == null)
                        _logDirectory = searchForLogDirectory(new File("/u"));
                    if (_logDirectory == null)
                        _logDirectory = searchForLogDirectory(new File("/v"));
                    if (_logDirectory == null)
                        _logDirectory = searchForLogDirectory(new File("/x"));
                    if (_logDirectory == null)
                        _logDirectory = searchForLogDirectory(new File("/y"));
                    if (_logDirectory == null) {
                        _logDirectory = new File("/home/lvuser/logs");
                        if (!_logDirectory.exists()) {
                            _logDirectory.mkdir();
                        }
                    }
                    String logMessage = String.format("Log directory is %s\n",
                            _logDirectory);
                    System.out.print(logMessage);
                }
            }
        }
        return _logDirectory;
    }

    static File searchForLogDirectory(File root) {
        // does the root directory exist?
        if (!root.isDirectory())
            return null;

        File logDirectory = new File(root, "logs");
        if (!logDirectory.isDirectory())
            return null;

        return logDirectory;
    }
}