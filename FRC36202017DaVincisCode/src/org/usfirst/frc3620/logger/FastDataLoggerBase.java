package org.usfirst.frc3620.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

abstract public class FastDataLoggerBase extends DataLoggerBase
        implements IFastDataLogger {

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
            if (t > t0 + maxLengthInSeconds)
                done();

            Object[] data = iDataLoggerDataProvider.fetchData();
            if (data != null)
                logData(t - t0, data);
        }
    }

    @Override
    public void done() {
        timer.cancel();
        System.out.println("fastLogger done");
        try {
            PrintWriter w = new PrintWriter(new FileWriter(outputFile));

            w.print("timestamp");
            String[] names = iDataLoggerDataProvider.fetchNames();
            for (String n : names) {
                w.print(",");
                w.print(n);
            }
            w.println();

            for (String n : metadata.keySet()) {
                w.print("# ");
                w.print(n);
                w.print(" = ");
                w.print(metadata.get(n));
                w.println();
            }

            writeData(w);

            w.close();

            isDone = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract void logData(double timestamp, Object[] d);

    abstract void writeData(PrintWriter w);
}
