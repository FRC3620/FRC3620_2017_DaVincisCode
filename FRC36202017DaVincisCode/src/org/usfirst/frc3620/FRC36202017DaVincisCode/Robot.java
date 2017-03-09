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

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.text.DecimalFormat;

import org.slf4j.Logger;


import java.io.IOException;


import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.*;
import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.*;
import org.usfirst.frc3620.misc.AverageSendableChooser;
import org.usfirst.frc3620.logger.DataLogger;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;
import org.usfirst.frc3620.misc.CANDeviceFinder;
import org.usfirst.frc3620.misc.RobotMode;
import org.usfirst.frc3620.logger.RandomFastLogger;
import org.usfirst.frc3620.vision.UDPReceiver;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// 3620 stuff
	static RobotMode currentRobotMode = RobotMode.INIT, previousRobotMode;

	static Logger logger;
	public static DataLogger robotDataLogger;
	public static DriverStation driverStation = DriverStation.getInstance();
	public static boolean pdpIsPresent;
	public static CANDeviceFinder canDeviceFinder;
	public static PowerDistributionPanel powerDistributionPanel = new PowerDistributionPanel();
	public static BuiltInAccelerometer builtinAccelerometer = new BuiltInAccelerometer();

	public static AverageSendableChooser autoChooser;
    public static AverageSendableChooser laneChooser;
	// this came with stock RobotBuilder stuff
	Command autonomousCommand;

	public static OI oi;
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static DriveSubsystem driveSubsystem;
	public static ShooterSubsystem shooterSubsystem;
	public static GearSubsystem gearSubsystem;
	public static ClimberSubsystem climberSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static MentorSubsystem mentorSubsystem;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// --------------------------------------------------------------
		// set up logging
		logger = EventLogging.getLogger(Robot.class, Level.INFO);

		canDeviceFinder = new CANDeviceFinder();
		logger.info("CAN devices = {}", canDeviceFinder.getDeviceList());
		pdpIsPresent = canDeviceFinder.isPDPPresent();
		logger.info("PDP present = {}", pdpIsPresent);

		// --------------------------------------------------------------
		// regular RobotBuilder stuff

		
		RobotMap.init();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        try
   		{
   			new UDPReceiver().start();
   		} catch (IOException e)
   		{
   			e.printStackTrace();
   		}
        
        
        // start a camera server so we can send pictures to the drivers
//        CameraServer.getInstance().startAutomaticCapture();

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		driveSubsystem = new DriveSubsystem();
		shooterSubsystem = new ShooterSubsystem();
		gearSubsystem = new GearSubsystem();
		climberSubsystem = new ClimberSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		mentorSubsystem = new MentorSubsystem();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

		autonomousCommand = new AutonomousCommand();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

		// --------------------------------------------------------------
		// finish up 3620 stuff
		robotDataLogger = new DataLogger();
		addRobotDataLoggerDataProviders();
		
		robotDataLogger.setInterval(1.000);
		robotDataLogger.start();
		
		
		// testing only!
		// RandomFastLogger.startRandomFastLogger("random.test");
		
		loadAutoChooser();
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
	}

	public static void loadAutoChooser() {
        laneChooser = new AverageSendableChooser();
        laneChooser.addDefault("Do Nothing", new AutonomousDoNothingCommand());
        laneChooser.addObject("Right", new AutoPointSenecaLane1());
        laneChooser.addObject("Middle", new AutoPointSenecaLane2());
        laneChooser.addObject("Left", new AutoPointSenecaLane3());
        laneChooser.addObject("DriveForward", new AutoPointSenecaLane4());
        SmartDashboard.putData("Lane chooser", laneChooser);
        autoChooser = new AverageSendableChooser();
        autoChooser.addObject("Back Up", new AutoBackUpFromPegCommand());
        autoChooser.addObject("Score From Boiler", new AutoScoreFromBoilerCommand());
        autoChooser.addObject("Move Down Field Left", new AutoMoveDownFieldLeftCommand());
        autoChooser.addObject("Move Down Field Right", new AutoMoveDownFieldRightCommand());
        
	}
	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		processRobotModeChange(RobotMode.DISABLED);
	}

    /**
     * This function is called periodically during operator control
     */


	public void disabledPeriodic() {
		beginPeriodic();
		Scheduler.getInstance().run();
		endPeriodic();
	}

	public void autonomousInit() {
		processRobotModeChange(RobotMode.AUTONOMOUS);
		Command command1 = (Command) laneChooser.getSelected();
		Command command2 = (Command) autoChooser.getSelected();		
		
	    try {
			    autonomousCommand = CombinationAutonomousStation.make(command1, command2);
	    } catch (Exception e) {
	        logger.error("Unable to make a SuperDuperAutonomous: {}", e);
	    }
	
		
		// autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null) {
			logger.info("Starting autonomous {}", autonomousCommand);
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		beginPeriodic();
		Scheduler.getInstance().run();
		endPeriodic();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Robot.driveSubsystem.shiftIntoHighGear();
		processRobotModeChange(RobotMode.TELEOP);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		beginPeriodic();
		Scheduler.getInstance().run();
		endPeriodic();
	}


	public void testInit() {
		// This makes sure that the autonomous stops running when
		// test starts running.
		if (autonomousCommand != null)
			((Command) autonomousCommand).cancel();

		processRobotModeChange(RobotMode.TEST);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		beginPeriodic();
		LiveWindow.run();
		endPeriodic();
	}

	/*
	 * this routine gets called whenever we change modes
	 */
	void processRobotModeChange(RobotMode newMode) {
		logger.info("Switching from {} to {}", currentRobotMode, newMode);
		previousRobotMode = currentRobotMode;
		currentRobotMode = newMode;

		// if any subsystems need to know about mode changes, let
		// them know here.
		driveSubsystem.processRobotModeChange(newMode);
	}

	/*
	 * these routines get called at the beginning and end of all periodics.
	 */
	void beginPeriodic() {
		// if some subsystems need to get called in all modes at the beginning
		// of periodic, do it here

		// don't need to do anything
	}

	void endPeriodic() {
		// if some subsystems need to get called in all modes at the end
		// of periodic, do it here

		// and log data!
		// updateDashboard();
//		SmartDashboard.putString("currentCommand", driveSubsystem.getCurrentCommand().getName());
		gearSubsystem.updateDashboard();
		shooterSubsystem.updateDashboard();
		driveSubsystem.updateDashboard();
		
		
		SmartDashboard.putNumber("driver y joystick", -Robot.oi.driveJoystick.getRawAxis(1));
		SmartDashboard.putNumber("driver x joystick", Robot.oi.driveJoystick.getRawAxis(4));
		SmartDashboard.putNumber("accel.x", builtinAccelerometer.getX());
		SmartDashboard.putNumber("accel.y", builtinAccelerometer.getY());
		SmartDashboard.putNumber("accel.z", builtinAccelerometer.getZ());
	}

	void addRobotDataLoggerDataProviders() {
		robotDataLogger.addDataProvider("robotMode", () -> currentRobotMode.toString());
		robotDataLogger.addDataProvider("robotModeInt", () -> currentRobotMode.ordinal());
		robotDataLogger.addDataProvider("batteryVoltage", () -> f2(driverStation.getBatteryVoltage()));

		if (false && pdpIsPresent) {
			robotDataLogger.addDataProvider("pdp.totalCurrent", () -> f2(powerDistributionPanel.getTotalCurrent()));
			robotDataLogger.addDataProvider("pdp.totalPower", () -> f2(powerDistributionPanel.getTotalPower()));
			robotDataLogger.addDataProvider("pdp.totalEnergy", () -> f2(powerDistributionPanel.getTotalEnergy()));
			
			robotDataLogger.addDataProvider("drive.l0.current", () -> f2(powerDistributionPanel.getCurrent(13)));
			robotDataLogger.addDataProvider("drive.l1.current", () -> f2(powerDistributionPanel.getCurrent(14)));
			robotDataLogger.addDataProvider("drive.l2.current", () -> f2(powerDistributionPanel.getCurrent(15)));

			robotDataLogger.addDataProvider("drive.r3.current", () -> f2(powerDistributionPanel.getCurrent(2)));
			robotDataLogger.addDataProvider("drive.r4.current", () -> f2(powerDistributionPanel.getCurrent(1)));
			robotDataLogger.addDataProvider("drive.r5.current", () -> f2(powerDistributionPanel.getCurrent(0)));
		}
		robotDataLogger.addDataProvider("drive.l0.power", () -> f2(RobotMap.driveSubsystemLeftDriveMotor1.get()));
		robotDataLogger.addDataProvider("drive.l1.power", () -> f2(RobotMap.driveSubsystemLeftDriveMotor2.get()));
		robotDataLogger.addDataProvider("drive.l2.power", () -> f2(RobotMap.driveSubsystemLeftDriveMotor3.get()));

		robotDataLogger.addDataProvider("drive.r3.power", () -> f2(RobotMap.driveSubsystemRightDriveMotor1.get()));
		robotDataLogger.addDataProvider("drive.r4.power", () -> f2(RobotMap.driveSubsystemRightDriveMotor2.get()));
		robotDataLogger.addDataProvider("drive.r5.power", () -> f2(RobotMap.driveSubsystemRightDriveMotor3.get()));

		if (canDeviceFinder.isSRXPresent(RobotMap.shooterSubsystemShooterCANTalon2)) {
			robotDataLogger.addDataProvider("shooter.t2.current", () -> f2(RobotMap.shooterSubsystemShooterCANTalon2.getOutputCurrent()));
			robotDataLogger.addDataProvider("shooter.t2.voltage", () -> f2(RobotMap.shooterSubsystemShooterCANTalon2.getOutputVoltage()));
		}

		if (canDeviceFinder.isSRXPresent(RobotMap.shooterSubsystemShooterCANTalon3)) {
			robotDataLogger.addDataProvider("shooter.t3.current", () -> f2(RobotMap.shooterSubsystemShooterCANTalon3.getOutputCurrent()));
			robotDataLogger.addDataProvider("shooter.t3.voltage", () -> f2(RobotMap.shooterSubsystemShooterCANTalon3.getOutputVoltage()));
		}
		
		robotDataLogger.addDataProvider("bi.accel.x", () -> f2(builtinAccelerometer.getX()));
		robotDataLogger.addDataProvider("bi.accel.y", () -> f2(builtinAccelerometer.getY()));
		robotDataLogger.addDataProvider("bi.accel.z", () -> f2(builtinAccelerometer.getZ()));

	}

	private DecimalFormat f2Formatter = new DecimalFormat("#.##");

	private String f2(double f) {
		String rv = f2Formatter.format(f);
		return rv;
	}
}
