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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

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
    public static CANTalon shooterSubsystemShooterCANTalon2;
    public static CANTalon shooterSubsystemShooterCANTalon3;
    public static SpeedController shooterSubsystemShooterImpellerTalon;
    public static SpeedController gearSubsystemLeftRightTalon;
    public static SpeedController gearSubsystemTiltTalon;
    public static DoubleSolenoid gearSubsystemGearPlunger;
    public static CANTalon climberSubsystemWinchCANTalon;
    public static SpeedController intakeSubsystemIntakeTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveSubsystemLeftDriveMotor1 = new Talon(0);
        LiveWindow.addActuator("DriveSubsystem", "Left Drive Motor 1 ", (Talon) driveSubsystemLeftDriveMotor1);
        
        driveSubsystemLeftDriveMotor2 = new Talon(1);
        LiveWindow.addActuator("DriveSubsystem", "Left Drive Motor 2", (Talon) driveSubsystemLeftDriveMotor2);
        
        driveSubsystemRightDriveMotor1 = new Talon(3);
        LiveWindow.addActuator("DriveSubsystem", "Right Drive Motor 1", (Talon) driveSubsystemRightDriveMotor1);
        
        driveSubsystemRightDriveMotor2 = new Talon(4);
        LiveWindow.addActuator("DriveSubsystem", "Right Drive Motor 2", (Talon) driveSubsystemRightDriveMotor2);
        
        driveSubsystemRobotDrive = new RobotDrive(driveSubsystemLeftDriveMotor1, driveSubsystemLeftDriveMotor2,
              driveSubsystemRightDriveMotor1, driveSubsystemRightDriveMotor2);
        
        driveSubsystemRobotDrive.setSafetyEnabled(true);
        driveSubsystemRobotDrive.setExpiration(0.1);
        driveSubsystemRobotDrive.setSensitivity(0.5);
        driveSubsystemRobotDrive.setMaxOutput(1.0);

        driveSubsystemLeftDriveMotor3 = new Talon(2);
        LiveWindow.addActuator("DriveSubsystem", "Left Drive Motor 3", (Talon) driveSubsystemLeftDriveMotor3);
        
        driveSubsystemRightDriveMotor3 = new Talon(5);
        LiveWindow.addActuator("DriveSubsystem", "Right Drive Motor 3", (Talon) driveSubsystemRightDriveMotor3);
        
        driveSubsystemShifterSolenoid = new DoubleSolenoid(0, 0, 1);
        LiveWindow.addActuator("DriveSubsystem", "Shifter Solenoid", driveSubsystemShifterSolenoid);
        
        shooterSubsystemShooterCANTalon2 = new CANTalon(2);
        LiveWindow.addActuator("ShooterSubsystem", "Shooter CAN Talon 2", shooterSubsystemShooterCANTalon2);
        
        shooterSubsystemShooterCANTalon3 = new CANTalon(3);
        LiveWindow.addActuator("ShooterSubsystem", "Shooter CAN Talon 3", shooterSubsystemShooterCANTalon3);
        
        shooterSubsystemShooterImpellerTalon = new Talon(6);
        LiveWindow.addActuator("ShooterSubsystem", "Shooter Impeller Talon", (Talon) shooterSubsystemShooterImpellerTalon);
        
        gearSubsystemLeftRightTalon = new Talon(7);
        LiveWindow.addActuator("GearSubsystem", "LeftRight Talon", (Talon) gearSubsystemLeftRightTalon);
        
        gearSubsystemTiltTalon = new Talon(8);
        LiveWindow.addActuator("GearSubsystem", "Tilt Talon", (Talon) gearSubsystemTiltTalon);
        
        gearSubsystemGearPlunger = new DoubleSolenoid(0, 2, 3);
        LiveWindow.addActuator("GearSubsystem", "GearPlunger", gearSubsystemGearPlunger);
        
        climberSubsystemWinchCANTalon = new CANTalon(8);
        LiveWindow.addActuator("ClimberSubsystem", "Winch CAN Talon", climberSubsystemWinchCANTalon);
        
        intakeSubsystemIntakeTalon = new Talon(9);
        LiveWindow.addActuator("IntakeSubsystem", "Intake Talon", (Talon) intakeSubsystemIntakeTalon);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
