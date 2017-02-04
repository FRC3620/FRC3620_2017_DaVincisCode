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
import org.usfirst.frc3620.vision.UDPReceiver;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class GearSubsystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftRightTalon = RobotMap.gearSubsystemLeftRightTalon;
    private final SpeedController tiltTalon = RobotMap.gearSubsystemTiltTalon;
    private final DoubleSolenoid gearPlunger = RobotMap.gearSubsystemGearPlunger;
    private final DigitalInput gearLimitLeft = RobotMap.gearSubsystemGearLimitLeft;
    private final DigitalInput gearLimitRight = RobotMap.gearSubsystemGearLimitRight;
    private final DigitalInput gearLimitUp = RobotMap.gearSubsystemGearLimitUp;
    private final DigitalInput gearLimitDown = RobotMap.gearSubsystemGearLimitDown;
    private final DoubleSolenoid gearSupport = RobotMap.gearSubsystemGearSupport;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void slideMotor(double speed) {

		if (speed > 0) {
			// we are trying to go to the right
			if (isRightLimitSwitchDown()) {
				// we are up against the right limit switch, so turn off motor
				leftRightTalon.set(0.0);
			} else {
				// we are not up against the right limit swtich, so run the motor
				// at whatever speed we were asked for
				leftRightTalon.set(speed);
			}
			
		} else {

			// we are trying to go to the left
			if (isLeftLimitSwitchDown()) {
				// we are up against the left limit switch, so turn off motor
				leftRightTalon.set(0.0);
			} else {
				// we are not up against the left limit swtich, so run the motor
				// at whatever speed we were asked for
				leftRightTalon.set(speed);
			}
		}

	}
    
    public void tiltMotor(double speed) {

		if (speed > 0) {
			// we are trying to go up
			if (isTopLimitSwitchDown()) {
				// we are up against the top limit switch, so turn off motor
				tiltTalon.set(0.0);
			} else {
				// we are not up against the top limit swtich, so run the motor
				// at whatever speed we were asked for
				tiltTalon.set(speed);
			}
			
		} else {

			// we are trying to go down
			if (isBottomLimitSwitchDown()) {
				// we are up against the bottom limit switch, so turn off motor
				tiltTalon.set(0.0);
			} else {
				// we are not up against the bottom limit swtich, so run the motor
				// at whatever speed we were asked for
				tiltTalon.set(speed);
			}
		}

	}
	
	public boolean isLeftLimitSwitchDown() {
		if (RobotMap.gearSubsystemGearLimitLeft.get() == false) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isRightLimitSwitchDown() {
		if (RobotMap.gearSubsystemGearLimitRight.get() == false) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isTopLimitSwitchDown() {
		if (RobotMap.gearSubsystemGearLimitUp.get() == false) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isBottomLimitSwitchDown() {
		if (RobotMap.gearSubsystemGearLimitDown.get() == false) {
			return true;
		} else {
			return false;
		}
	}
    
    public void plungeGear(){
    	gearPlunger.set(Value.kForward);
    }
   
    public void retractGearPlunger(){
    	gearPlunger.set(Value.kReverse);
    }
    
    public void stopGearPlunger(){
    	gearPlunger.set(Value.kOff);
    }
    
    public void retractGearSupport(){
    	gearSupport.set(Value.kForward);
    }
   
    public void extendGearSupport(){
    	gearSupport.set(Value.kReverse);
    }
    
    public void stopGearSupport(){
    	gearSupport.set(Value.kOff);
    }
    
    public void rotateGear(){
    	double gearRotatorSpeed = .40;
    	if(Robot.oi.operatorJoystick.getRawAxis(1)> .2){
    		tiltMotor(gearRotatorSpeed);
    	}
    	else if(Robot.oi.operatorJoystick.getRawAxis(1)< -.2){
    	tiltMotor(-gearRotatorSpeed);
    	}
    	else{
    		tiltTalon.set(0);
    	}
    }
    
    public void stopGearShift(){
    	leftRightTalon.set(0);
    	}
    
    public void stopGearRotate(){
    	tiltTalon.set(0);
    	}
    
    //*****************************************VISION************************************************
    
	public double getTargetCenter() {
		if (UDPReceiver.visionData == null) return 0;
		return UDPReceiver.visionData.getX();
	}
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        setDefaultCommand(new ShiftGearCommand());
    	 //setDefaultCommand(new RotateGearManualCommand());
    }
}

