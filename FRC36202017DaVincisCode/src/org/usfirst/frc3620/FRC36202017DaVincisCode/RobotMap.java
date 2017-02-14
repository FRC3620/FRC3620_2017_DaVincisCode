// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3620.FRC36202017DaVincisCode;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveSubsystemLeftDriveMotor1;
    public static SpeedController driveSubsystemLeftDriveMotor2;
    public static SpeedController driveSubsystemRightDriveMotor1;
    public static SpeedController driveSubsystemRightDriveMotor2;
    public static RobotDrive driveSubsystemRobotDrive;
    public static SpeedController driveSubsystemLeftDriveMotor3;
    public static SpeedController driveSubsystemRightDriveMotor3;
    public static DoubleSolenoid driveSubsystemShifterSolenoid;
    public static Encoder driveSubsystemRightEncoder;
    public static Encoder driveSubsystemLeftEncoder;
    public static AnalogInput driveSubsystemRangeFinder;
    public static CANTalon shooterSubsystemShooterCANTalon2;
    public static CANTalon shooterSubsystemShooterCANTalon3;
    public static SpeedController shooterSubsystemShooterImpellerTalon;
    public static SpeedController gearSubsystemLeftRightTalon;
    public static SpeedController gearSubsystemTiltTalon;
    public static DoubleSolenoid gearSubsystemGearPlunger;
    public static DigitalInput gearSubsystemGearLimitLeft;
    public static DigitalInput gearSubsystemGearLimitRight;
    public static DigitalInput gearSubsystemGearLimitUp;
    public static DigitalInput gearSubsystemGearLimitDown;
    public static DoubleSolenoid gearSubsystemGearSupport;
    public static DoubleSolenoid climberSubsystemRopeIntake;
    public static DigitalInput climberSubsystemTheTopOfRope;
    public static SpeedController intakeSubsystemIntakeTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveSubsystemLeftDriveMotor1 = new Spark(0);
        LiveWindow.addActuator("DriveSubsystem", "Left Drive Motor 1 ", (Spark) driveSubsystemLeftDriveMotor1);
        
        driveSubsystemLeftDriveMotor2 = new Spark(1);
        LiveWindow.addActuator("DriveSubsystem", "Left Drive Motor 2", (Spark) driveSubsystemLeftDriveMotor2);
        
        driveSubsystemRightDriveMotor1 = new Spark(3);
        LiveWindow.addActuator("DriveSubsystem", "Right Drive Motor 1", (Spark) driveSubsystemRightDriveMotor1);
        
        driveSubsystemRightDriveMotor2 = new Spark(4);
        LiveWindow.addActuator("DriveSubsystem", "Right Drive Motor 2", (Spark) driveSubsystemRightDriveMotor2);
        
        driveSubsystemRobotDrive = new RobotDrive(driveSubsystemLeftDriveMotor1, driveSubsystemLeftDriveMotor2,
              driveSubsystemRightDriveMotor1, driveSubsystemRightDriveMotor2);
        
        driveSubsystemRobotDrive.setSafetyEnabled(true);
        driveSubsystemRobotDrive.setExpiration(0.1);
        driveSubsystemRobotDrive.setSensitivity(0.5);
        driveSubsystemRobotDrive.setMaxOutput(1.0);
        driveSubsystemRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        driveSubsystemRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        driveSubsystemRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveSubsystemRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        driveSubsystemLeftDriveMotor3 = new Spark(2);
        LiveWindow.addActuator("DriveSubsystem", "Left Drive Motor 3", (Spark) driveSubsystemLeftDriveMotor3);
        
        driveSubsystemRightDriveMotor3 = new Spark(5);
        LiveWindow.addActuator("DriveSubsystem", "Right Drive Motor 3", (Spark) driveSubsystemRightDriveMotor3);
        
        driveSubsystemShifterSolenoid = new DoubleSolenoid(0, 4, 5);
        LiveWindow.addActuator("DriveSubsystem", "Shifter Solenoid", driveSubsystemShifterSolenoid);
        
        driveSubsystemRightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveSubsystem", "Right Encoder", driveSubsystemRightEncoder);
        driveSubsystemRightEncoder.setDistancePerPulse(1.0);
        driveSubsystemRightEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveSubsystemLeftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveSubsystem", "Left Encoder", driveSubsystemLeftEncoder);
        driveSubsystemLeftEncoder.setDistancePerPulse(1.0);
        driveSubsystemLeftEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveSubsystemRangeFinder = new AnalogInput(0);
        LiveWindow.addSensor("DriveSubsystem", "RangeFinder", driveSubsystemRangeFinder);
        
        shooterSubsystemShooterCANTalon2 = new CANTalon(2);
        LiveWindow.addActuator("ShooterSubsystem", "Shooter CAN Talon 2", shooterSubsystemShooterCANTalon2);
        
        shooterSubsystemShooterCANTalon3 = new CANTalon(3);
        LiveWindow.addActuator("ShooterSubsystem", "Shooter CAN Talon 3", shooterSubsystemShooterCANTalon3);
        
        shooterSubsystemShooterImpellerTalon = new Spark(6);
        LiveWindow.addActuator("ShooterSubsystem", "Shooter Impeller Talon", (Spark) shooterSubsystemShooterImpellerTalon);
        
        gearSubsystemLeftRightTalon = new Spark(7);
        LiveWindow.addActuator("GearSubsystem", "LeftRight Talon", (Spark) gearSubsystemLeftRightTalon);
        
        gearSubsystemTiltTalon = new Spark(8);
        LiveWindow.addActuator("GearSubsystem", "Tilt Talon", (Spark) gearSubsystemTiltTalon);
        
        gearSubsystemGearPlunger = new DoubleSolenoid(0, 2, 3);
        LiveWindow.addActuator("GearSubsystem", "GearPlunger", gearSubsystemGearPlunger);
        
        gearSubsystemGearLimitLeft = new DigitalInput(4);
        LiveWindow.addSensor("GearSubsystem", "GearLimitLeft", gearSubsystemGearLimitLeft);
        
        gearSubsystemGearLimitRight = new DigitalInput(5);
        LiveWindow.addSensor("GearSubsystem", "GearLimitRight", gearSubsystemGearLimitRight);
        
        gearSubsystemGearLimitUp = new DigitalInput(6);
        LiveWindow.addSensor("GearSubsystem", "GearLimitUp", gearSubsystemGearLimitUp);
        
        gearSubsystemGearLimitDown = new DigitalInput(7);
        LiveWindow.addSensor("GearSubsystem", "GearLimitDown", gearSubsystemGearLimitDown);
        
        gearSubsystemGearSupport = new DoubleSolenoid(0, 0, 1);
        LiveWindow.addActuator("GearSubsystem", "GearSupport", gearSubsystemGearSupport);
        
        climberSubsystemRopeIntake = new DoubleSolenoid(0, 6, 7);
        LiveWindow.addActuator("ClimberSubsystem", "RopeIntake", climberSubsystemRopeIntake);
        
        climberSubsystemTheTopOfRope = new DigitalInput(8);
        LiveWindow.addSensor("ClimberSubsystem", "TheTopOfRope", climberSubsystemTheTopOfRope);
        
        intakeSubsystemIntakeTalon = new Spark(9);
        LiveWindow.addActuator("IntakeSubsystem", "Intake Talon", (Spark) intakeSubsystemIntakeTalon);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
