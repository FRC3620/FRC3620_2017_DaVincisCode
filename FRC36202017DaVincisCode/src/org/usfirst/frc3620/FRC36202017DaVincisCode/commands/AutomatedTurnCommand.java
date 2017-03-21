package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;


import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.DriveSubsystem;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 */
public class AutomatedTurnCommand extends Command implements PIDOutput{
	
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	
	double sideStick;
	
	double howFarWeWantToTurn = 0;
	
	//PIDController pidTurn = new PIDController(.025, 00, 00, kF, ahrs, this); undershot
	//PIDController pidTurn = new PIDController(.025, .001, 00, kF, ahrs, this); overshot
	//PIDController pidTurn = new PIDController(.035, .001, 00, kF, ahrs, this); overshot
	//PIDController pidTurn = new PIDController(.015, .001, 00, kF, ahrs, this); overshot
	//PIDController pidTurn = new PIDController(.015, .0001, 00, kF, ahrs, this); works
//	PIDController pidTurn = new PIDController(.1, .0001, .00, .00, Robot.driveSubsystem.getAhrsPidSource(), this);
	PIDController pidTurn = new PIDController(100, 20, 0, .00, Robot.driveSubsystem.getAhrsPidSource(), this);
	public AutomatedTurnCommand() {
		this(90.0);
	}

    public AutomatedTurnCommand(double howFar) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	
    	pidTurn.setInputRange(0.0f,  360.0f);
    	pidTurn.setOutputRange(-1, 1);
    	pidTurn.setContinuous(true);
    	
    	howFarWeWantToTurn = howFar;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	logger.info("AutomatedTurn start");
    	
    	double angle = Robot.driveSubsystem.getAutomaticHeading();
    	double newAngle = Robot.driveSubsystem.changeAutomaticHeading(howFarWeWantToTurn);
    	logger.info("angle was {}, new setpoint is {}", angle, newAngle);
    	// TODO We need to look at this
    	pidTurn.setSetpoint(newAngle);
    	logger.info("we rechecked the setpoint = {}", pidTurn.getSetpoint());
    	pidTurn.reset();
    	pidTurn.setAbsoluteTolerance(10.0);
    	pidTurn.enable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.updateDashboardWithPidStuff(this, pidTurn, sideStick);

    	Robot.driveSubsystem.driveAutomatically(0, sideStick);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// TODO figure out why this is broken
        //return pidTurn.onTarget();
    	double want = Robot.driveSubsystem.getAutomaticHeading();
    	double got = Robot.driveSubsystem.getAngle();
    	double error = DriveSubsystem.angleDifference(want, got);
    	logger.info("want {}, got {}, error {}, ontarget {}, getAvgError {}, getError {}", want, got, error, pidTurn.onTarget(),
    			pidTurn.getAvgError(),
    			pidTurn.getError());
    	
    	return error < 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("AutomatedTurn end");
    	
    	pidTurn.disable();
    	Robot.driveSubsystem.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("AutomatedTurn interrupted");
    	
    	end();
    }
    
    public void pidWrite(double output) {
       sideStick = output;
    }

   
}
