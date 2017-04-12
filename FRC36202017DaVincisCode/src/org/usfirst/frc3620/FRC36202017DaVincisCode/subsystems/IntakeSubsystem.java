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
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController intakeTalonLeft = RobotMap.intakeSubsystemIntakeTalon;
    private final SpeedController intakeTalonRight = RobotMap.shooterSubsystemShooterImpellerTalon;
    
	private final DoubleSolenoid pickUpShifter = RobotMap.intakeSubsystemFloorPickup;


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	

    // Put methods for controlling this subsystem
    // here. Call these from C
    public void initDefaultCommand() {
    	
    	
    	
    	
    	
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void runIntakeMotor(double power) {
    	intakeTalonLeft.set(power);
    	intakeTalonRight.set(-power);
    	
    	
    }
    
    public void stopIntakeMotor() {
    	intakeTalonLeft.set(0);
    	intakeTalonRight.set(0);
    	
    	
    }
    
    public void dropGearPickup(){
    	pickUpShifter.set(Value.kForward);    	
    }
    
    public void liftGearPickup(){
    	pickUpShifter.set(Value.kReverse);
    	
    }
    public void stopGearPickup(){
    	pickUpShifter.set(Value.kOff);
    	
    }
}

