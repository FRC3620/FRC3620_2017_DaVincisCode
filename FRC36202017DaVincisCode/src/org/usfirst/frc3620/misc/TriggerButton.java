package org.usfirst.frc3620.misc;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class TriggerButton extends Button {
	Joystick stick;
	boolean isLeft;
	double deadZone;

	public TriggerButton(Joystick joystick, boolean isLeft, double deadZone) {
		this.isLeft = isLeft;
		stick = joystick;
		this.deadZone = deadZone;
	}

	public boolean get() {
		if (isLeft) {
			return Math.abs(stick.getRawAxis(2)) > deadZone;
		} else {
			return Math.abs(stick.getRawAxis(3)) > deadZone;
		}

	}

}
