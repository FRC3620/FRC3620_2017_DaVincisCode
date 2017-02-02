package org.usfirst.frc3620.logger;

public interface IFastDataLogger extends IDataLogger {
    public void setMaxLength(double seconds);

    public void done();

    public boolean isDone();
}
