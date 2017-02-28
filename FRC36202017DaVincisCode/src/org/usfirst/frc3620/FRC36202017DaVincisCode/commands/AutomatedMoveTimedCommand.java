package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.RobotMap;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutomatedMoveTimedCommand extends Command implements PIDOutput{
	
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	

	//Last working kP = .05
	static final double kP = .05;
	//Last working kI = .0015
	static final double kI = .0015;	
	//Last working kD = .02
	static final double kD = .02;
	
	static final double kF = .00;
	double sideStick;
	
	double howLongWeWantToMove = 0;
	double howFastToMove = 0;
	
	Timer timer = new Timer();
	
	PIDController pidDriveStraight = new PIDController(kP, kI, kD, kF, Robot.driveSubsystem.getAhrsPidSource(), this);
	

    public AutomatedMoveTimedCommand(double howLongInSeconds, double howFast) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	pidDriveStraight.setOutputRange(-1, 1);
    	pidDriveStraight.setInputRange(0.0f, 360.0f);
    	pidDriveStraight.setContinuous(true);
    	howFastToMove = howFast;
    	howLongWeWantToMove = howLongInSeconds;
    }
    
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("AutomatedMoveTimed start");
    	
    	RobotMap.driveSubsystemLeftEncoder.reset();
    	RobotMap.driveSubsystemRightEncoder.reset();
        pidDriveStraight.setSetpoint(Robot.driveSubsystem.getAutomaticHeading());
        pidDriveStraight.reset();
        pidDriveStraight.enable();
    	
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.updateDashboardWithPidStuff(this, pidDriveStraight, sideStick);

    	Robot.driveSubsystem.driveAutomatically(howFastToMove, sideStick);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return timer.get() > howLongWeWantToMove;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	logger.info("AutomatedMoveTimed end");
    	
    	pidDriveStraight.disable();
    	Robot.driveSubsystem.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	logger.info("AutomatedMoveTimed interrupted");
    	
        end();
    }
      
    public void pidWrite(double output) {
       sideStick = output;
    }

   
}
