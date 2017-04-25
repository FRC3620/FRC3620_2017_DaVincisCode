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
public class ClampGearCommand extends Command {

	Timer timer = new Timer();
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	
    public ClampGearCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("clamping gear");
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get()>.5){
    		Robot.intakeSubsystem.liftGearPickup();
    	}
    	else if(timer.get()>.1){
    		Robot.intakeSubsystem.clampGear();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return timer.get()>.5;
//    	return true;
    	return timer.hasPeriodPassed(.8);
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
