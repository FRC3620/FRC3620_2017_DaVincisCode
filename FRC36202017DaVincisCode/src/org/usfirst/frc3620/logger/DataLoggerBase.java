package org.usfirst.frc3620.logger;

import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.usfirst.frc3620.logger.EventLogging.Level;

abstract public class DataLoggerBase implements IDataLogger {
    IDataLoggerDataProvider iDataLoggerDataProvider;
    File loggingDirectory = LoggingMaster.getLoggingDirectory();
    String filename = "";

    Logger logger = EventLogging.getLogger(getClass(), Level.INFO);

    Map<String, Double> metadata = new TreeMap<>();

    double intervalInSeconds = 0.100;

    File outputFile;
    Timer timer;

    @Override
    public void setLoggingDirectory(File loggerDirectory) {
        this.loggingDirectory = loggerDirectory;
    }

    @Override
    public void setDataProvider(
            IDataLoggerDataProvider _iFastLoggerDataProvider) {
        iDataLoggerDataProvider = _iFastLoggerDataProvider;
    }

    @Override
    public void setFilename(String _filename) {
        filename = _filename;
    }

    @Override
    public void addMetadata(String s, double d) {
        metadata.put(s, d);
    }

    @Override
    public void setInterval(double seconds) {
        intervalInSeconds = seconds;
    }

    double getTimeInSeconds() {
        return edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
        // return System.currentTimeMillis() / 1000.0;
    }

    @Override
    public String start() {
        setupOutputFile();

        startTimer();

        if (outputFile == null) {
            return "";
        } else {
            return outputFile.getAbsolutePath();
        }
    }

    void setupOutputFile() {
        String _timestampString = LoggingMaster.getTimestampString();
        if (_timestampString != null) {
            String fullFilename = filename + _timestampString + ".csv";
            outputFile = new File(loggingDirectory, fullFilename);
        }
    }

    abstract void startTimer();
}
