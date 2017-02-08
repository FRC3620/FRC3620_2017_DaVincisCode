package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShiftGearToPegCommand extends Command {

    public ShiftGearToPegCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putNumber("Blob Count", Robot.gearSubsystem.getBlobCount());
    	SmartDashboard.putNumber("X Offset", Robot.gearSubsystem.xOffset());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearSubsystem.shiftGearToPeg();
    	System.out.println("shifting Gear to Peg");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        SmartDashboard.putBoolean("Gear Aligned", Robot.gearSubsystem.gearIsAligned());
    	return Robot.gearSubsystem.gearIsAligned();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearSubsystem.stopGearShift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
