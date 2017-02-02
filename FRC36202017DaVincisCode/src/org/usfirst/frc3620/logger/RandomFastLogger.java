package org.usfirst.frc3620.logger;

import org.usfirst.frc3620.logger.FastDataLoggerCollections;

public class RandomFastLogger {
    public static void startRandomFastLogger(String name) {
        IFastDataLogger iFastDataLogger;

        iFastDataLogger = new FastDataLoggerCollections();
        iFastDataLogger.setInterval(0.001);
        iFastDataLogger.setMaxLength(5.000);
        iFastDataLogger.setFilename(name);
        iFastDataLogger.addMetadata("pi", Math.PI);
        iFastDataLogger.addMetadata("e", Math.E);

        iFastDataLogger.addDataProvider("r1", () -> Math.random());
        iFastDataLogger.addDataProvider("r2", () -> Math.random() * 2.0);
        iFastDataLogger.addDataProvider("r3", () -> Math.random() * 3.0);

        iFastDataLogger.start();
    }
}
