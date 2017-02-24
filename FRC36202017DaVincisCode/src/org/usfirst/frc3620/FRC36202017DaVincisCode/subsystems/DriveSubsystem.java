
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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);

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
	private final DoubleSolenoid climbingShifterSolenoid = RobotMap.climberSubsystemRopeIntake;
	private final Encoder rightEncoder = RobotMap.driveSubsystemRightEncoder;
	private final Encoder leftEncoder = RobotMap.driveSubsystemLeftEncoder;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
		setDefaultCommand(new DriveCommand());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}

	public boolean weAreInReverse = false;

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

	public void processRobotModeChange(RobotMode robotMode) {
		if (robotMode == RobotMode.TELEOP
				|| robotMode == RobotMode.AUTONOMOUS) {
			resetNavX();
			logger.info("NavX is resetting");
			resetEncoders();
			automaticHeading = 0;
		}
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void resetNavX() {
		if (ahrs != null) {
			ahrs.resetDisplacement();
			logger.info("Resetting X Displacement, X = {}",
					ahrs.getDisplacementX());
			ahrs.reset();
			logger.info("Resetting NavX Angle, Angle = {}", ahrs.getAngle());
		}
	}

	boolean complainedAboutMissingAhrs = false;

	public PIDSource getAhrsPidSource() {
		if (ahrsIsConnected()) {
			return ahrs;
		} else {
			return new PIDSource() {

				@Override
				public void setPIDSourceType(PIDSourceType pidSource) {
					// TODO Auto-generated method stub

				}

				@Override
				public double pidGet() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public PIDSourceType getPIDSourceType() {
					// TODO Auto-generated method stub
					return null;
				}
			};
		}
	}

	public boolean ahrsIsConnected() {
		return ahrs != null && ahrs.isConnected();
	}

	void complainAboutMissingAhrs() {
		if (!complainedAboutMissingAhrs) {
			logger.warn("Cannot read NavX, not connected");
		}
		complainedAboutMissingAhrs = true;
	}

	public double getPitch() {
		if (ahrsIsConnected()) {
			return ahrs.getPitch();
		} else {
			complainAboutMissingAhrs();
			return 0;
		}
	}

	public double getRoll() {
		if (ahrsIsConnected()) {
			return ahrs.getRoll();
		} else {
			complainAboutMissingAhrs();
			return 0;
		}
	}

	public double getAngle() {
		if (ahrsIsConnected()) {
			return ahrs.getAngle();
		} else {
			complainAboutMissingAhrs();
			return 0;
		}
	}

	public double getDisplacementX() {
		if (ahrsIsConnected()) {
			return ahrs.getDisplacementX();
		} else {
			complainAboutMissingAhrs();
			return 0;
		}
	}

	public double getDisplacementY() {
		if (ahrsIsConnected()) {
			return ahrs.getDisplacementY();
		} else {
			complainAboutMissingAhrs();
			return 0;
		}
	}

	public double getDisplacementZ() {
		if (ahrsIsConnected()) {
			return ahrs.getDisplacementZ();
		} else {
			complainAboutMissingAhrs();
			return 0;
		}
	}

	public double getRangeInInches() {
		double voltage = RobotMap.driveSubsystemRangeFinder.getAverageVoltage();
		double inches = (6 / .06) * voltage;
		return inches;
	}

	public double robotOffset() {
		return (18 - getRangeInInches());
	}

	public void setUpRobotForGearScoring() {
		if (robotIsAligned()) {
			SmartDashboard.putBoolean("Robot Is Aligned", true);
			stopDrivingNow();
		} else if (robotOffset() < 0) {
			SmartDashboard.putBoolean("Robot Is Aligned", false);
			setDriveForward(.75, 0);
		} else if (robotOffset() > 0) {
			SmartDashboard.putBoolean("Robot Is Aligned", false);
			setDriveForward(-.75, 0);
		}
	}

	public void moveRobotForwardToPlunge() {
		if (robotIsShovedUp()) {
			SmartDashboard.putBoolean("Robot Is Shoved Up", true);
			stopDrivingNow();
		} else {
			setDriveForward(.75, 0);
		}
	}

	public boolean robotIsAligned() {
		if (Math.abs(robotOffset()) < 2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean robotIsShovedUp() {
		if (getRangeInInches() < 2) {
			return true;
		} else {
			return false;
		}
	}

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

	public void updateDashboard() {
		SmartDashboard.putString("DriveSubsystemCurrentCommand",
				"" + getCurrentCommand());
		SmartDashboard.putNumber("Drive Encoder Left",
				leftEncoder.getDistance());
		SmartDashboard.putNumber("Drive Encoder Right",
				rightEncoder.getDistance());
		SmartDashboard.putNumber("Right Motor 1", rightDriveMotor1.get());
		SmartDashboard.putNumber("Right Motor 2", rightDriveMotor2.get());
		SmartDashboard.putNumber("Right Motor 3", rightDriveMotor3.get());

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

	protected static double limit(double num) {
		if (num > 1.0) {
			return 1.0;
		}
		if (num < -1.0) {
			return -1.0;
		}
		return num;
	}
	
	public void winchOnly (double moveValue) {
		if (weAreInReverse) {
			moveValue = -moveValue;
		}
		moveValue = limit(moveValue);
		if (moveValue < 0) {
			moveValue = 0.0;
		}

		SmartDashboard.putNumber("moveValue", moveValue);
		SmartDashboard.putNumber("rotateValue", 0);

		robotDrive.setLeftRightMotorOutputs(0.0, moveValue);
		fixThirdMotor();
	}

	public void winchArcadeDrive(double moveValue, double rotateValue,
			boolean squaredInputs) {

		if (weAreInReverse) {
			moveValue = -moveValue;
		}
		// local variables to hold the computed PWM values for the motors
		double leftMotorSpeed;
		double rightMotorSpeed;

		moveValue = limit(moveValue);
		rotateValue = limit(rotateValue);

		if (squaredInputs) {
			// square the inputs (while preserving the sign) to increase fine
			// control
			// while permitting full power
			if (moveValue >= 0.0) {
				moveValue = moveValue * moveValue;
			} else {
				moveValue = -(moveValue * moveValue);
			}
			if (rotateValue >= 0.0) {
				rotateValue = rotateValue * rotateValue;
			} else {
				rotateValue = -(rotateValue * rotateValue);
			}
		}

		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
		if (rightMotorSpeed < 0) {
			rightMotorSpeed = 0.0;
		}

		SmartDashboard.putNumber("moveValue", moveValue);
		SmartDashboard.putNumber("rotateValue", rotateValue);
		robotDrive.setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		fixThirdMotor();
	}

	public void shiftIntoClimbingMode() {
		climbingShifterSolenoid.set(Value.kForward);
	}

	public void shiftOutOfClimbingMode() {
		climbingShifterSolenoid.set(Value.kReverse);
	}

	public boolean isRobotTipped() {
		if (ahrsIsConnected()) {
			return (Math.abs(getRoll()) > 20 || Math.abs(getPitch()) > 20);
		} else {
			double z = Robot.builtinAccelerometer.getZ();
			return Math.abs(z) < 0.85;
		}
	}
}