package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.DriveSubsystem;
import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.GearSubsystem;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPlungeGearCommand extends Command {

    public AutoPlungeGearCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.gearSubsystem);
        requires(Robot.driveSubsystem);
    }

    Timer timer = new Timer();
    Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	Robot.gearSubsystem.extendGearSupport();
    	Robot.gearSubsystem.retractGearPlunger();
    	logger.info("AutoPlungerGear initialize()");
    	System.out.println(timer.get()
    			);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.gearSubsystem.plungeGear();
//    	Robot.driveSubsystem.setDriveForward(0, 0);

    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.hasPeriodPassed(.5);
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("AutoPlungerGear end()");
    	timer.stop();
    	timer.reset();
    	Robot.gearSubsystem.stopGearPlunger();
    	Robot.gearSubsystem.stopGearSupport();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("AutoPlungerGear interrupted()");
    	end();
    }
}
