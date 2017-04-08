package org.usfirst.frc3620.misc;

import org.slf4j.Logger;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.Timer;

public class SlewRateLimiter {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	
	double lastP;
	double lastT;
	double maxSlew;
	boolean firstTime = true;
	
	double currentTime(){
		return Timer.getFPGATimestamp();
	}
	
	public void setFirstTime(){
		firstTime = true;
	}
	
	public void setMaxSlew(double slewRate){
		maxSlew = slewRate;
	}
	
	public boolean sameSign(double a, double b){
		boolean rv;
		if(Math.signum(a) == 0 || Math.signum(b) == 0){
			rv = true;
		}
		else if(Math.signum(a) == Math.signum(b)){
			rv = true;
		}
		else {
			rv = false;
		}
		logger.debug("same sign {} {} -> {}", a, b, rv);
		return rv;
	}
	
	public double limitIt(double requestedP){
		double limitedP = requestedP;
		double now = currentTime();
		if(firstTime){
			lastT = now - 0.02;
			lastP = 0;
			logger.debug("Resetting lastP");
		}
		
		firstTime = false;
		
		double deltaP = requestedP - lastP;
		logger.debug("lastP = {}, requestedP = {}, deltaP = {}", lastP, requestedP, deltaP);
		
		boolean limiting = false;
		if(sameSign(requestedP, lastP) && (Math.abs(requestedP) > Math.abs(lastP))){
			limiting = true;
			logger.debug("Same Sign, increasing power");
		}
		else if(!sameSign(requestedP, lastP)){
			limiting = true;
			lastP = 0;
			logger.debug("Signs Switched");
		}
		else{
			limiting = false;
		}
		
		if(limiting){
			logger.debug("limiting");
			double deltaT =  now - lastT;
			if(deltaT > 0){
				double slope = deltaP/deltaT;
				if(Math.abs(slope) > maxSlew){
					limitedP = lastP + (deltaT*maxSlew*Math.signum(slope));
					logger.debug("lastP = {}, deltaT = {}, maxSlew = {}", lastP, deltaT, maxSlew);
				}
			}
		}
		lastP = limitedP;
		lastT = now;
		logger.debug("limitedP = {}, requestedP = {}", limitedP, requestedP);
		return limitedP;
		
	}
}
