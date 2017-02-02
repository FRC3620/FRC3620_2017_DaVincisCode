package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.GearSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PlungeGearCommand extends Command {

    public PlungeGearCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.gearSubsystem);
    }

    Timer timer = new Timer();
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	Robot.gearSubsystem.retractGearSupport();
    	Robot.gearSubsystem.retractGearPlunger();
    	System.out.println(timer.get()
    			);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearSubsystem.extendGearSupport();
    	if(timer.get() > .25){
    	Robot.gearSubsystem.plungeGear();
    	}
    	
    	if(timer.get() > .5){
    		Robot.gearSubsystem.retractGearPlunger();
    		Robot.gearSubsystem.retractGearSupport();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.hasPeriodPassed(1);
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	timer.reset();
    	Robot.gearSubsystem.stopGearPlunger();
    	Robot.gearSubsystem.stopGearSupport();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
