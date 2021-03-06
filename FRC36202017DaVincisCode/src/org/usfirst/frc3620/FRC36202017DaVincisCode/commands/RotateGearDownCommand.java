package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateGearDownCommand extends Command {

    public RotateGearDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearSubsystem.tiltMotor(-.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gearSubsystem.isBottomLimitSwitchDown();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearSubsystem.stopGearRotate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
