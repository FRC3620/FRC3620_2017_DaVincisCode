package org.usfirst.frc3620.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

abstract public class FastDataLoggerBase extends DataLoggerBase implements IFastDataLogger {

	double maxLengthInSeconds;
	double t0;

	boolean isDone = false;

	@Override
	public void setMaxLength(double seconds) {
		maxLengthInSeconds = seconds;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void startTimer() {
		isDone = false;

		t0 = getTimeInSeconds();

		timer = new Timer();
		long interval = Math.min(1, Math.round(intervalInSeconds * 1000));
		System.out.println("fastlogger interval = " + interval);
		timer.schedule(new FastLoggerTimerTask(), 0, interval);
	}

	class FastLoggerTimerTask extends TimerTask {
		@Override
		public void run() {
			double t = getTimeInSeconds();
			if (t > t0 + maxLengthInSeconds) {
				done();
			} else {
				Object[] row = new Object[namedDataProviders.size()];

				for (int i = 0; i < row.length; i++) {
					NamedDataProvider namedDataProvider = namedDataProviders.get(i);
					try {
						row[i] = namedDataProvider.iDataLoggerDataProvider.get();
					} catch (Exception e) {
						row[i] = "ERROR";
					}
				}

				logData(t - t0, row);
			}
		}
	}

	@Override
	public void done() {
		timer.cancel();
		if (outputFile != null) {
			logger.info("fastLogger done, writing to {}", outputFile);
			try {
				PrintWriter w = new PrintWriter(new FileWriter(outputFile));
				writeHeader(w, namedDataProviders, metadata);
				writeData(w);
				w.close();

				isDone = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			logger.warn("no output file yet!");
		}
	}

	abstract void logData(double timestamp, Object[] row);

	abstract void writeData(PrintWriter w);
}
