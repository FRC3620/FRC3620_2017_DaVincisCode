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
public class AutoDoublecheckVisionCommand extends Command {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	Timer timer = new Timer();
	 boolean robotTooLeft;
	 boolean robotTooRight;
	 double howFastToMove;

    public AutoDoublecheckVisionCommand(double howFast) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	howFastToMove = howFast;
    }
   
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	logger.info("Double-checking vision");
    	 robotTooLeft=false;
    	 robotTooRight=false;
    	timer.start();
    	timer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.driveAutomatically(howFastToMove, 0);
    	if(Robot.gearSubsystem.isRightLimitSwitchDown())
			robotTooLeft=true;
    	
    	if(Robot.gearSubsystem.isLeftLimitSwitchDown())
			robotTooRight=true;
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get()>.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(robotTooRight && robotTooLeft){
    		logger.warn("ERROR: Both switches pressed");
    	}else if(robotTooLeft){
    		logger.info("Robot too left");
    		Robot.driveSubsystem.changeAutomaticHeading(7);
    	}
    	else if(robotTooRight){
    		logger.info("Robot too right");
    		Robot.driveSubsystem.changeAutomaticHeading(-7);
    	}
    	else{
    		logger.info("Robot aligned");
    	}
    	
    	logger.info("Vision double-checked");
    	//Gear is Aligned
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
