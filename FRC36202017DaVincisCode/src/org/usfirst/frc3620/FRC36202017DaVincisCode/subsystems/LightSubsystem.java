package org.usfirst.frc3620.FRC36202017DaVincisCode.subsystems;

import org.usfirst.frc3620.FRC36202017DaVincisCode.Robot;
import org.usfirst.frc3620.FRC36202017DaVincisCode.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LightSubsystem extends Subsystem {
    private final Relay spike0 = RobotMap.lightSubsystemSpike0;
    private final Relay spike1 = RobotMap.lightSubsystemSpike1;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private boolean headLight = false;
    private boolean red = false;
    private boolean blue = false;
    private boolean green = false;

    public void initDefaultCommand() {
    }
    
    public void setHeadlight (boolean b) {
    	headLight = b;
    	hitRelays();
    }
    
    public void setRed (boolean b) {
    	red = b;
    	hitRelays();
    }
    
    public void setGreen (boolean b) {
    	green = b;
    	hitRelays();
    }
    
    public void setBlue (boolean b) {
    	blue = b;
    	hitRelays();
    }
    
    /*public void setColor (int color) {
        red = (color & 0xff0000) > 0x800000;
        green = (color & 0x00ff00) > 0x008000;
        blue = (color & 0x0000ff) > 0x000080;
        hitRelays();
    }*/
    
    public void setColor (Color color) {
    	red = color.r() > 127;
    	green = color.g() > 127;
    	blue = color.b() > 127;
    	hitRelays();
    }

    void hitRelays() {
    	Relay.Value s0 = Value.kOff;
    	if (red) {
    		if (green) {
    			s0 = Value.kOn;
    		} else {
    			// red only
    			s0 = Value.kForward;
    		}
    	} else {
    		if (green) {
			  s0 = Value.kReverse;
    		}
    	}
    	spike0.set(s0);
    	
    	Relay.Value s1 = Value.kOff;
    	if (blue) {
    		if (headLight) {
    			s1 = Value.kOn;
    		} else {
    			// blue only
    			s1 = Value.kForward;
    		}
    	} else {
    		if (headLight) {
    			// headlight only
			    s1 = Value.kReverse;
    		}
    	}
    	spike1.set(s1);
    }
    
    public enum Color {
    	RED(0xff0000), BLUE(0x0000ff), GREEN(0x00ff00), YELLOW(0xffff00), OFF(0x000000);
    	
    	int rgb;
    	Color (int _rgb) {
    		rgb = _rgb;
    	}
    	public int r() {
    		return (rgb & 0xff0000) >> 16;
    	}
    	public int g() {
    		return (rgb & 0x00ff00) >> 8;
    	}
    	public int b() {
    		return (rgb & 0x0000ff);
    	}
    }
}

