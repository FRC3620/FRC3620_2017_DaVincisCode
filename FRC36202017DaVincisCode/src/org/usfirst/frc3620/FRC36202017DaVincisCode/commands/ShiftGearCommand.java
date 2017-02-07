package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftGearCommand extends Command {

    public ShiftGearCommand() {
    	requires(Robot.gearSubsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double gearShiftRightSpeed = Robot.oi.operatorJoystick.getRawAxis(3);
    	double gearShiftLeftSpeed = Robot.oi.operatorJoystick.getRawAxis(2);
    	if(gearShiftRightSpeed> .2){
    		Robot.gearSubsystem.slideMotor(gearShiftRightSpeed);
    	}
    	else if(gearShiftLeftSpeed> .2){
    		Robot.gearSubsystem.slideMotor(-gearShiftLeftSpeed);
    	}
    	else{
    		Robot.gearSubsystem.slideMotor(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
