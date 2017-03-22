package org.usfirst.frc3620.FRC36202017DaVincisCode;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;
import org.usfirst.frc3620.misc.AverageSendableChooser;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Preferences;

public class ControlPanelWatcher {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	
	Preferences preferences = Preferences.getInstance();

	Timer timer = new Timer();
	Joystick controlPanel = /* new Joystick(2) */ null;
	PersistentChooser beforeGearPersister, gearPersister, afterGearPersister;

	class PersistentChooser {
		AverageSendableChooser chooser;
		List<String> chooserNames;
		String nameOfPreference;
		
		public PersistentChooser(AverageSendableChooser c, String n) {
			chooser = c;
			chooserNames = chooser.getChoiceNames();
			nameOfPreference = n;
			
			String nameFromPreferences = preferences.getString(nameOfPreference, null);
			if (nameFromPreferences != null) {
				logger.info("setting chooser {} to {} from preferences", nameOfPreference, nameFromPreferences);
				chooser.select(nameFromPreferences);
			}
		}
		
		public void setFromControlPanel(int controlPanelIndex) {
			String selectedName = chooser.getSelectedName();
			int chooserIndex = chooserNames.indexOf(selectedName);
			if (controlPanelIndex != chooserIndex) {
				if (controlPanelIndex < chooserNames.size()) {
					String controlPanelName = chooserNames.get(controlPanelIndex);
                    String chooserName = chooserNames.get(chooserIndex);
					logger.info("chooser {} says autonomous {} ({}), control panel says {} ({}), updating chooser to {}",
							nameOfPreference, chooserIndex, chooserName, controlPanelIndex, controlPanelName, controlPanelName);

					// update chooser here
					chooser.select(controlPanelName);
					selectedName = controlPanelName;
				} else {
					logger.info("control panel for chooser {} says autonomous {}, don't have that many", nameOfPreference, controlPanelIndex);
				}
			}
		}
		
		public void persist() {
			String preferencesSelectedName = preferences.getString(nameOfPreference, chooserNames.get(0));
			String selectedName = chooser.getSelectedName();
			if (!selectedName.equals(preferencesSelectedName)) {
				logger.info("changing {} in preferences from {} to {}", nameOfPreference, preferencesSelectedName, selectedName);
				preferences.putString(nameOfPreference, selectedName);
			}
		}
	}
	
	public ControlPanelWatcher() {
		beforeGearPersister = new PersistentChooser(Robot.beforeGearChooser, "beforeGearChooser");
		gearPersister = new PersistentChooser(Robot.gearChooser, "gearChooser");
		afterGearPersister = new PersistentChooser(Robot.afterGearChooser, "afterGearChooser");
		
		if (controlPanel != null) {
			logger.info("Control Panel appears to be a {}", controlPanel.getName());
		}
		
		// look at the control panel every 2000 ms (2 seconds)
		timer.schedule(new MyTimerTask(), 0, 2000);
	}

	class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			boolean controlPanelPresent = false;
			if (controlPanel != null && 
				controlPanel.getName().equals("Arduino Leonardo") && 
				controlPanel.getAxis(AxisType.kZ) > 0.95) {
					controlPanelPresent = true;
				    logger.debug("The z axis is {}", controlPanel.getAxis(AxisType.kZ));
			}
			
			if (controlPanelPresent) {
				// the control panel is connected to the driver's station
				int controlPanelIndex;
				
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				// BEGINNING OF UNTESTED CODE
				controlPanelIndex = readBeforeGearFromControlPanel();
				logger.debug("BeforeGear control panel autonomous is {}", controlPanelIndex);
				beforeGearPersister.setFromControlPanel (controlPanelIndex);

				controlPanelIndex = readGearFromControlPanel();
				logger.debug("Gear control panel autonomous is {}", controlPanelIndex);
				gearPersister.setFromControlPanel (controlPanelIndex);

				controlPanelIndex = readAfterGearFromControlPanel();
				logger.debug("Gear control panel autonomous is {}", controlPanelIndex);
				afterGearPersister.setFromControlPanel (controlPanelIndex);
				// END OF UNTESTED CODE
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			}
			
			beforeGearPersister.persist();
			gearPersister.persist();
			afterGearPersister.persist();
		}
	}

	int readBeforeGearFromControlPanel() {
		int rv = 0;
		// these bits are 7, 8, 9, 10 on the Arduino end
		for (int i = 11; i >= 8; i--) {
			rv = rv << 1;
			boolean b = controlPanel.getRawButton(i);
			if (b)
				rv += 1;
			logger.debug("button {} = {}, beforeGear is now {}", i, b, rv);
		}
		return rv;
	}

    int readGearFromControlPanel() {
        int rv = 0;
        // these bits are 4, 5, 6 on the Arduino end
        for (int i = 7; i >= 5; i--) {
            rv = rv << 1;
            boolean b = controlPanel.getRawButton(i);
            if (b)
                rv += 1;
            logger.debug("button {} = {}, gear is now {}", i, b, rv);
        }
        return rv;
    }

    int readAfterGearFromControlPanel() {
        int rv = 0;
        // these bits are 3, 2 on the Arduino end
        for (int i = 4; i >= 3; i--) {
            rv = rv << 1;
            boolean b = controlPanel.getRawButton(i);
            if (b)
                rv += 1;
            logger.debug("button {} = {}, afterGear is now {}", i, b, rv);
        }
        return rv;
    }

}