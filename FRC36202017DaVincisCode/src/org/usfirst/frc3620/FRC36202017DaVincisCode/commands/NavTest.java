package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NavTest extends Command {

    public NavTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.runWinch(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { 
    	double roll = Robot.driveSubsystem.getRoll();
    	System.out.println("Roll is " + roll);
    	if(roll > 50){
    		return true;
    	}
    	if(roll < -50){
    		return true;
    	}
    	double pitch = Robot.driveSubsystem.getPitch();
    	System.out.println("Pitch is " + pitch);
    	if(pitch > 50){
    		return true;
    	}
    	if(pitch < -50){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.runWinch(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.runWinch(0);
    }
    }

