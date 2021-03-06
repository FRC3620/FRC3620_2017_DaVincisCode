package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.DriveSubsystem;
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
//        requires(Robot.driveSubsystem);
    }

    Timer timer = new Timer();
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	Robot.intakeSubsystem.dropGearPickup();
    	Robot.intakeSubsystem.unclampGear();
    	Robot.gearSubsystem.extendGearSupport();
    	Robot.gearSubsystem.retractGearPlunger();
    	System.out.println(timer.get()
    			);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(timer.get()>3){
    		Robot.gearSubsystem.retractGearSupport();
    	}
    	else if(timer.get() > .5){
    		Robot.gearSubsystem.retractGearPlunger();
    	}
    	else if(timer.get() > .25){
    	
    	Robot.gearSubsystem.plungeGear();
//    	Robot.driveSubsystem.setDriveForward(0, 0);
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.hasPeriodPassed(3.5);
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
