package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShiftGearToPegCommand extends Command {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);

    public ShiftGearToPegCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("ShiftGearToPegCount started");
    	SmartDashboard.putNumber("Blob Count", Robot.gearSubsystem.getBlobCount());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearSubsystem.shiftGearToPeg();
    	
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       
    	return Robot.driveSubsystem.getRangeInInches()<15;
//    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("ShiftGearToPegCount ended");
    	Robot.gearSubsystem.stopGearShift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("ShiftGearToPegCount interrupted");
    	end();
    }
}
