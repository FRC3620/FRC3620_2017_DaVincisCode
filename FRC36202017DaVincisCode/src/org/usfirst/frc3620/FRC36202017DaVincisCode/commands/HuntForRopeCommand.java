package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.slf4j.Logger;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HuntForRopeCommand extends Command {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	Command finishWinchCommand = new FinishWinchCommand();
    public HuntForRopeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.weAreInReverse=true;
    	logger.info("HuntForRopeCommand start");
    	Robot.driveSubsystem.shiftIntoLowGear();
    	Robot.driveSubsystem.shiftIntoClimbingMode();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (! Robot.driveSubsystem.isRobotTipped()) {
    	  Robot.driveSubsystem.winchArcadeDrive(Robot.oi.driveJoystick.getRawAxis(1), Robot.oi.driveJoystick.getRawAxis(4), false);
    	} else {
      	  Robot.driveSubsystem.winchOnly(Robot.oi.driveJoystick.getRawAxis(1));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { 
//    	if (Robot.driveSubsystem.ahrsIsConnected()) {
//        	double roll = Robot.driveSubsystem.getRoll();
//        	if(roll > 30){
//        		logger.info("finishing: roll is {}", roll);
//        		return true;
//        	}
//        	if(roll < -30){
//        		logger.info("finishing: roll is {}", roll);
//        		return true;
//        	}
//        	double pitch = Robot.driveSubsystem.getPitch();
//        	if(pitch > 20){
//        		logger.info("finishing: pitch is {}", pitch);
//        		return true;
//        	}
//        	if(pitch < -20){
//        		logger.info("finishing: pitch is {}", pitch);
//        		return true;
//        	}
//            return false;
//    	} else {
//    		double x = Robot.builtinAccelerometer.getX();
//    		if (Math.abs(x) > 0.3) {
//        		logger.info("finishing: accel X is {}", x);
//    			return true;
//    		}
//    		double y = Robot.builtinAccelerometer.getX();
//    		if (Math.abs(y) > 0.3) {
//        		logger.info("finishing: accel Y is {}", y);
//    			return true;
//    		}
//    		return false;
//    	}
    	return false;

    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("HuntForRopeCommand end");
//    	finishWinchCommand.start();
    	Robot.driveSubsystem.runWinch(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("HuntForRopeCommand interrupted");
    	Robot.driveSubsystem.shiftOutOfClimbingMode();
    	Robot.driveSubsystem.runWinch(0);
    }
}