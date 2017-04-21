package org.usfirst.frc3620.FRC36202017DaVincisCode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.*;

import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.AutoGearMiddleCommand;
import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.AutoGearLeftRedCommand;
import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.AutoDriveForwardCommand;
import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.AutoGearRightRedCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CombinationAutonomousStation extends CommandGroup {

	public static Command make(Command command1, Command command2, Command command3)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		CombinationAutonomousStation combinationStation = new CombinationAutonomousStation();
		if (command1 != null) {
		    Command newCommand1 = makeANewCommand(command1);
			combinationStation.addSequential(newCommand1);
			combinationStation.commands.add(newCommand1);
		}
		if (command2 != null) {
		    Command newCommand2 = makeANewCommand(command2);
			combinationStation.addSequential(newCommand2);
			combinationStation.commands.add(newCommand2);
		}
		if (command3 != null) {
		    Command newCommand3 = makeANewCommand(command3);
			combinationStation.addSequential(newCommand3);
			combinationStation.commands.add(newCommand3);
		}
		
		return combinationStation;
	}

	List<Command> commands = new ArrayList<>();

	@Override
	public String toString() {
		return "combinationStationAutonomousMaker [commands=" + commands + "]";
	}

	static Command makeANewCommand(Command c) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = c.getClass();
		Constructor<?> ctor = clazz.getConstructor();
		Object object = ctor.newInstance(new Object[] {});
		return (Command) object;
	}

}
