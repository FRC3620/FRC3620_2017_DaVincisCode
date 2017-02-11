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

import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

	public Joystick driveJoystick;
	public JoystickButton shiftUpButton, shiftDownButton, switchBackAndForthButton;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton runShooterButton;
    public JoystickButton rotateGearUpButton;
    public JoystickButton rotateGearDownButton;
    public JoystickButton plungeGearButton;
    public JoystickButton closeGearSupportButton;
    public JoystickButton openGearSupportButton;
    public JoystickButton visionButton;
    public JoystickButton feedShooterButton;
    public Joystick operatorJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
    	driveJoystick = new Joystick(0);
    	
        shiftDownButton = new JoystickButton(driveJoystick, 1);
        shiftDownButton.whenPressed(new ShiftDownCommand());
        shiftUpButton = new JoystickButton(driveJoystick, 4);
        shiftUpButton.whenPressed(new ShiftUpCommand());
        
        switchBackAndForthButton = new JoystickButton(driveJoystick, 6);
        switchBackAndForthButton.whenPressed(new SwitchBackAndForthCommand());
       

    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operatorJoystick = new Joystick(1);
        
        runShooterButton = new JoystickButton(operatorJoystick, 4);
        runShooterButton.toggleWhenPressed(new RunShooterCommand());
        
//        rotateGearUpButton = new JoystickButton(operatorJoystick, 4);
//        rotateGearUpButton.whenPressed(new RotateGearUpCommand());
//        
//        rotateGearDownButton = new JoystickButton(operatorJoystick, 3);
//        rotateGearDownButton.whenPressed(new RotateGearDownCommand());
        
        plungeGearButton = new JoystickButton(operatorJoystick, 2);
        plungeGearButton.whenPressed(new PlungeGearCommand());
        
        
        openGearSupportButton = new JoystickButton(operatorJoystick, 9);
        openGearSupportButton.toggleWhenPressed(new OpenGearSupportCommand());
        
        visionButton = new JoystickButton(operatorJoystick, 1);
        visionButton.toggleWhenPressed(new ShiftGearToPegCommand());
        
        feedShooterButton = new JoystickButton(operatorJoystick, 3);
        feedShooterButton.whileHeld(new FeedShooterCommand());

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("RunShooterCommand", new RunShooterCommand());

        SmartDashboard.putData("SwitchBackAndForth", new SwitchBackAndForthCommand());
        SmartDashboard.putData("ScoreGear", new ScoreGearCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        SmartDashboard.putData("RunIntakeCommand", new RunIntakeCommand());
        SmartDashboard.putData("NavTest", new RunWinchUntilTiltedCommand());
        SmartDashboard.putData("Time", new RunWinchForTimeCommand()); 
        SmartDashboard.putData("Order", new WinchOrderCommand());
        SmartDashboard.putData("TestAutomatedMove", new AutomatedMoveCommand(5,0.8));
        SmartDashboard.putData("AutomatedTurn", new AutomatedTurnCommand(30));
        SmartDashboard.putData("AutomatedTurnLeft", new AutomatedTurnCommand(-30));
        SmartDashboard.putData("Watch", new AutomatedMoveTimedCommand(20.0, 0.00)); 
        SmartDashboard.putData("AutoTest", new AutoTestingTurnAndMoveCommand());

    }
    

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getOperatorJoystick() {
        return operatorJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

