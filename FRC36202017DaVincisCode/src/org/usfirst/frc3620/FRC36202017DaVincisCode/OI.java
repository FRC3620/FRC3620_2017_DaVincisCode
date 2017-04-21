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
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems.*;
import org.usfirst.frc3620.misc.DPad;
import org.usfirst.frc3620.misc.TriggerButton;


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
	public TriggerButton shiftUpButton, shiftDownButton;
	public JoystickButton switchBackAndForthButton;
	public JoystickButton shiftClimberButton;
    public JoystickButton runShooterButton;
    public JoystickButton rotateGearUpButton;
    public JoystickButton rotateGearDownButton;
    public JoystickButton plungeGearButton;
    public JoystickButton closeGearSupportButton;
    public JoystickButton openGearSupportButton;
    public JoystickButton visionButton;
    public JoystickButton feedShooterButton;
    public JoystickButton shiftOutOfClimberButton;
    public DPad dropGearPickUpButton;
    public DPad liftGearPickUpButton;
    public TriggerButton runIntakeButton;
    public TriggerButton releaseIntakeButton;
    public JoystickButton senecaLane3Button;
    
    public Joystick operatorJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
    	driveJoystick = new Joystick(0);
    	
        shiftDownButton = new TriggerButton(driveJoystick, true, .2);
        shiftDownButton.whenPressed(new ShiftDownCommand());
        shiftUpButton = new TriggerButton(driveJoystick, false, .2);
        shiftUpButton.whenPressed(new ShiftUpCommand());
        
        Command huntForRopeCommand = new HuntForRopeCommand();
        shiftClimberButton = new JoystickButton(driveJoystick, 3);
        shiftClimberButton.whenActive(huntForRopeCommand);
        shiftOutOfClimberButton = new JoystickButton(driveJoystick, 2);
        shiftOutOfClimberButton.cancelWhenActive(huntForRopeCommand);
        //into climber=x out of climber=b
        
        switchBackAndForthButton = new JoystickButton(driveJoystick, 6);
        switchBackAndForthButton.whenPressed(new SwitchBackAndForthCommand());
       

    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operatorJoystick = new Joystick(1);
        DPad operatorDpad = new DPad(operatorJoystick, 0);
        
        runShooterButton = new JoystickButton(operatorJoystick, 4);
        runShooterButton.toggleWhenPressed(new RunShooterCommand());
        
        plungeGearButton = new JoystickButton(operatorJoystick, 2);
        plungeGearButton.whenPressed(new PlungeGearCommand());
        
        
        openGearSupportButton = new JoystickButton(operatorJoystick, 9);
        openGearSupportButton.toggleWhenPressed(new OpenGearSupportCommand());
        
        visionButton = new JoystickButton(operatorJoystick, 1);
        visionButton.toggleWhenPressed(new ShiftGearToPegCommand());
        
        feedShooterButton = new JoystickButton(operatorJoystick, 3);
        feedShooterButton.whileHeld(new FeedShooterCommand());
        
        runIntakeButton = new TriggerButton(operatorJoystick, false, .2);
        runIntakeButton.whileHeld(new RunIntakeCommand());
        
        releaseIntakeButton = new TriggerButton(operatorJoystick, true, .2);
        releaseIntakeButton.whileHeld(new ReleaseGearCommand());
        
        operatorDpad.up().whenActive(new PickUpLiftCommand());
             
        operatorDpad.down().whenActive(new PickUpDropCommand());
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("RunShooterCommand", new RunShooterCommand());

        SmartDashboard.putData("SwitchBackAndForth", new SwitchBackAndForthCommand());
        SmartDashboard.putData("ScoreGear", new ScoreGearCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        SmartDashboard.putData("RunIntakeCommand", new RunIntakeCommand());
        SmartDashboard.putData("AutomatedTurn", new AutomatedTurnCommand(15));
        SmartDashboard.putData("AutomatedTurnLeft", new AutomatedTurnCommand(-30));
        SmartDashboard.putData("AutoScoreFeul", new AutoScoreFeulBoilerCommand());
        SmartDashboard.putData("Move Right", new RightDriveCommand(1, .9));
        SmartDashboard.putData("Move Left", new LeftDriveCommand(1, .9));
        
    }
    

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getOperatorJoystick() {
        return operatorJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

