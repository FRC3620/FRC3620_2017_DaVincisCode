package org.usfirst.frc3620.logger;

import org.usfirst.frc3620.logger.FastDataLoggerCollections;

public class CANTalonLogger {
	public static void startCANTalonPositionLogger(String name, final com.ctre.CANTalon talon) {
		IFastDataLogger iFastDataLogger;
		iFastDataLogger = new FastDataLoggerCollections();
		iFastDataLogger.setInterval(0.001);
		iFastDataLogger.setMaxLength(2.000);
		iFastDataLogger.setFilename(name);

		iFastDataLogger.addMetadata("P", talon.getP());
		iFastDataLogger.addMetadata("I", talon.getI());
		iFastDataLogger.addMetadata("D", talon.getD());
		iFastDataLogger.addMetadata("F", talon.getF());

		iFastDataLogger.addDataProvider("error", () -> talon.getError());
		iFastDataLogger.addDataProvider("outputCurrent", () -> talon.getOutputCurrent());
		iFastDataLogger.addDataProvider("outputVoltage", () -> talon.getOutputVoltage());

		iFastDataLogger.start();
	}
}
