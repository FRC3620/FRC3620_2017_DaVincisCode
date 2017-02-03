package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunWinchForTimeCommand extends Command {
	Timer timer = new Timer ();
	

    public RunWinchForTimeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.runWinch(0.8);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.hasPeriodPassed(2);
        
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
