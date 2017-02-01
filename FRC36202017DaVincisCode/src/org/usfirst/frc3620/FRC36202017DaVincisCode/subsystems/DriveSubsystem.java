// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems;


import org.usfirst.frc3620.FRC36202017DaVincisCode.RobotMap;
import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.*;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveSubsystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftDriveMotor1 = RobotMap.driveSubsystemLeftDriveMotor1;
    private final SpeedController leftDriveMotor2 = RobotMap.driveSubsystemLeftDriveMotor2;
    private final SpeedController rightDriveMotor1 = RobotMap.driveSubsystemRightDriveMotor1;
    private final SpeedController rightDriveMotor2 = RobotMap.driveSubsystemRightDriveMotor2;
    private final RobotDrive robotDrive = RobotMap.driveSubsystemRobotDrive;
    private final SpeedController leftDriveMotor3 = RobotMap.driveSubsystemLeftDriveMotor3;
    private final SpeedController rightDriveMotor3 = RobotMap.driveSubsystemRightDriveMotor3;
    private final DoubleSolenoid shifterSolenoid = RobotMap.driveSubsystemShifterSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public boolean weAreInReverse = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    	setDefaultCommand(new DriveCommand());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand()); 
    	setDefaultCommand(new DriveCommand());
    }
    
    public void driveControl (Joystick joystick) {
		robotDrive.arcadeDrive(joystick);
		fixThirdMotor();
	}

	public void driveControl(double move, double rotate){
		if(Math.abs(move) <= .2){
			move = 0;
		}
		
		if(Math.abs(rotate) <= .2){
			rotate = 0;
		}
		
		robotDrive.arcadeDrive(move, rotate);
		fixThirdMotor();
	}
		 public void setDriveForward(double move, double rotate) {
		double r2 = rotate * rotate;
    	if (rotate < 0) {
    		r2 = -r2; 
    	}
        
        if (weAreInReverse) {
            move = -move;
        }
        robotDrive.arcadeDrive(move , r2);

	}
	
	
	public void stopDrivingNow(){
		robotDrive.stopMotor();
		fixThirdMotor();
	}
	public void fixThirdMotor(){
		double motorpower;
		motorpower = rightDriveMotor1.get();
		rightDriveMotor3.set(motorpower);
		motorpower= leftDriveMotor1.get();
		leftDriveMotor3.set(motorpower);
		
	}
	
	public void shiftIntoHighGear() {
			shifterSolenoid.set(Value.kForward);
	}
	
	public void shiftIntoLowGear() {
		shifterSolenoid.set(Value.kReverse);
		
	}
}
