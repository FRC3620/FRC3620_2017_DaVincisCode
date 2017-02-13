package org.usfirst.frc3620.misc;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DPadTest {

	@Test
	public void testRight() {
		FakeHID fakeHID = new FakeHID(0);
		DPad dpad = new DPad(fakeHID, 0);
		Trigger trigger = dpad.right();
		
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 45 && i <= 135) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.up();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 46 && i <= 135) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.up();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 46 && i <= 135) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.down();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 46 && i <= 134) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
	}
	
	@Test
	public void testDown() {
		FakeHID fakeHID = new FakeHID(0);
		DPad dpad = new DPad(fakeHID, 0);
		Trigger trigger = dpad.down();
		
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 135 && i <= 225) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.right();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 136 && i <= 225) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.left();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 136 && i <= 224) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
	}
	
	@Test
	public void testLeft() {
		FakeHID fakeHID = new FakeHID(0);
		DPad dpad = new DPad(fakeHID, 0);
		Trigger trigger = dpad.left();
		
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 225 && i <= 315) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.down();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 226 && i <= 315) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.up();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 226 && i <= 314) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
	}
	
	@Test
	public void testUp() {
		FakeHID fakeHID = new FakeHID(0);
		DPad dpad = new DPad(fakeHID, 0);
		Trigger trigger = dpad.up();
		
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 315 || i <= 45) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.left();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 316 || i <= 45) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
		
		dpad.right();
		for (int i = 0; i <= 359; i++) {
			fakeHID.setPovValue(i);
			boolean r = trigger.get();
			if (i >= 316 || i <= 44) 
	  			  assertTrue("wrong value at " + i, r);
			else
	  			  assertFalse("wrong value at " + i, r);
		}
	}
	
	class FakeHID extends GenericHID {
		public FakeHID(int port) {
			super(port);
			// TODO Auto-generated constructor stub
		}

		private int povValue = -1;
		
		public void setPovValue(int i) {
			this.povValue = i;
		}

		@Override
		public double getX(Hand hand) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public double getY(Hand hand) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public double getRawAxis(int which) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean getRawButton(int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getPOV(int pov) {
			// TODO Auto-generated method stub
			return povValue;
		}

		@Override
		public int getPOVCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public HIDType getType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setOutput(int outputNumber, boolean value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setOutputs(int value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setRumble(RumbleType type, double value) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
