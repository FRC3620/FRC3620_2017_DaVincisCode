package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.RobotMap;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;


import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunWinchUntilTiltedCommand extends Command {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	
    public RunWinchUntilTiltedCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("RunWinchUntilTiltedCommand start");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.runWinch(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { 
    	double roll = Robot.driveSubsystem.getRoll();
    	System.out.println("Roll is " + roll);
    	if(roll > 30){
    		return true;
    	}
    	if(roll < -30){
    		return true;
    	}
    	double pitch = Robot.driveSubsystem.getPitch();
    	System.out.println("Pitch is " + pitch);
    	if(pitch > 20){
    		return true;
    	}
    	if(pitch < -20){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("RunWinchUntilTiltedCommand end");
    	
    	Robot.driveSubsystem.runWinch(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("RunWinchUntilTiltedCommand interrupted");
    	
    	Robot.driveSubsystem.runWinch(0);
    }
    }

