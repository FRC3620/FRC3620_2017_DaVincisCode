package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetUpForGearScoringCommand extends Command {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);

    public SetUpForGearScoringCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	logger.info("Setting up robot to score gear");
    	Robot.driveSubsystem.setUpRobotForGearScoring();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.robotIsAligned();
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("Robot set up to score gear");
    	Robot.driveSubsystem.stopDrivingNow();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
