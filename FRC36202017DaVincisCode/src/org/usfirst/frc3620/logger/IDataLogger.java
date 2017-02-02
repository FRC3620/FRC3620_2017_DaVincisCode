package org.usfirst.frc3620.logger;

import java.io.File;

public interface IDataLogger {
    public void setLoggingDirectory(File loggingDirectory);

    public void setFilename(String filename);

    public void setInterval(double seconds);

    public void addDataProvider(String name, 
            IDataLoggerDataProvider iDataLoggerDataProvider);

    public void addMetadata(String s, double d);

    public String start();

}
