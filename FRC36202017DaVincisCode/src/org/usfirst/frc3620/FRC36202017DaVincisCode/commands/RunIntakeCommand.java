package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.IntakeSubsystem;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntakeCommand extends Command {

	Timer timer = new Timer();
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	
    public RunIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("intaking gear");
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeSubsystem.runIntakeMotor(.85);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//      return (Robot.intakeSubsystem.isGearIn()||timer.get()>1.5);
//    	return timer.get()>1.5;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	logger.info("Intake command ended");
    	Robot.intakeSubsystem.runIntakeMotor(0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSubsystem.runIntakeMotor(0);
    	end();
    }
}
