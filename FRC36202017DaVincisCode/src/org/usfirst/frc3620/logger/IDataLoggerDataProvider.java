package org.usfirst.frc3620.logger;

public interface IDataLoggerDataProvider {
    public String[] fetchNames();

    public Object[] fetchData();
}
