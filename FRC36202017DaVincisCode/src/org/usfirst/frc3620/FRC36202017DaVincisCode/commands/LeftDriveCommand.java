package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeftDriveCommand extends Command {
	
	Timer timer = new Timer();
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	
	double howLongWeWantToMove;
	double howFastWeWantToMove;

    public LeftDriveCommand(double howLong, double howFast) {
    	requires(Robot.driveSubsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	howLongWeWantToMove = howLong;
    	howFastWeWantToMove = howFast;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	logger.info("Moving left side");
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.runLeftSide(howFastWeWantToMove);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        logger.info("Ending left drive");
    	return timer.get()>howLongWeWantToMove;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stopDrivingNow();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
