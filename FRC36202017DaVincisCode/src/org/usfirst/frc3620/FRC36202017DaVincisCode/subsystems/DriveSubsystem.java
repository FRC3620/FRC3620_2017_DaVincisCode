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

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.RobotMap;
import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.*;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;
import org.usfirst.frc3620.misc.RobotMode;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);

	public static AHRS ahrs = new AHRS(Port.kMXP);

	double automaticHeading = 0;

	static public double angleDifference(double angle1, double angle2) {
		double diff = Math.abs(angle1 - angle2);
		if (diff > 180) {
			diff = 360 - diff;
		}
		return diff;
	}

	public double getAutomaticHeading() {
		return automaticHeading;
	}

	static public double normalizeAngle(double angle) {
		// bring into range of -360..360
		double newAngle = angle % 360;

		// if it's between -360..0, put it between 0..360
		if (newAngle < 0)
			newAngle += 360;

		return newAngle;
	}

	public double changeAutomaticHeading(double changeAngle) {
		automaticHeading = automaticHeading + changeAngle;
		automaticHeading = normalizeAngle(automaticHeading);
		return automaticHeading;
	}

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
	private final Encoder rightEncoder = RobotMap.driveSubsystemRightEncoder;
	private final Encoder leftEncoder = RobotMap.driveSubsystemLeftEncoder;

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

	public void runWinch(double winchPower) {
		robotDrive.tankDrive(0, winchPower);
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
		robotDrive.arcadeDrive(move, r2);
		fixThirdMotor();

	}

	public void stopDrivingNow() {
		robotDrive.stopMotor();
		fixThirdMotor();
	}

	public void stopMotors() {
		robotDrive.stopMotor();
		fixThirdMotor();
	}

	public void processRobotModeChange(RobotMode robotMode)
	{
		if(robotMode==RobotMode.TELEOP || robotMode==RobotMode.AUTONOMOUS)
		{
			resetNavX();
			logger.info("NavX is resetting");
			resetEncoders();
			automaticHeading = 0;
		}
	}

	public void resetEncoders()
	{
		leftEncoder.reset();
		rightEncoder.reset();
		
	}

	public void resetNavX() 
	{
		ahrs.resetDisplacement();
		logger.info("Resetting X Displacement, X = {}", ahrs.getDisplacementX());
		ahrs.reset();
		logger.info("Resetting NavX Angle, Angle = {}", ahrs.getAngle());
	}
	
	public void fixThirdMotor() {
		double motorpower;
		motorpower = rightDriveMotor1.get();
		rightDriveMotor3.set(motorpower);
		motorpower = leftDriveMotor1.get();
		leftDriveMotor3.set(motorpower);
	}

	public void shiftIntoHighGear() {
		shifterSolenoid.set(Value.kForward);
	}

	public void shiftIntoLowGear() {
		shifterSolenoid.set(Value.kReverse);

	}

	public double getRoll() {
		return ahrs.getRoll();
	}

	public double getAngle() {
		return ahrs.getAngle();
	}

	public double getPitch() {
		return ahrs.getPitch();
	}

	public double getDisplacementX() {
		return ahrs.getDisplacementX();
	}

	public double getDisplacementY() {
		return ahrs.getDisplacementY();
	}

	public double getDisplacementZ() {
		return ahrs.getDisplacementZ();
	}

	public AHRS getAhrs() {
		return ahrs;
	}

	public void updateDashboardWithPidStuff(Command who, PIDController pid,
			double sidestick) {
		SmartDashboard.putString("PID Command", who.getName());
		SmartDashboard.putNumber("PID P", pid.getP());
		SmartDashboard.putNumber("PID I", pid.getI());
		SmartDashboard.putNumber("PID D", pid.getD());

		SmartDashboard.putNumber("PID Turn Sidestick", sidestick);
		SmartDashboard.putNumber("PID Angle Setpoint", pid.getSetpoint());
		SmartDashboard.putNumber("PID Angle Error", pid.getError());

		SmartDashboard.putNumber("ActualHeading", getAngle());
		SmartDashboard.putNumber("DesiredHeading", getAutomaticHeading());

	}
}
