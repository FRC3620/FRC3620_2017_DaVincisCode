package org.usfirst.frc3620.FRC36202017DaVincisCode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.*;

import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.AutoPointSenecaLane2;
import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.AutoPointSenecaLane3;
import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.AutoPointSenecaLane4;
import org.usfirst.frc3620.FRC36202017DaVincisCode.commands.AutoPointSenecaLane1;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CombinationAutonomousStation extends CommandGroup {

	public static Command make(Command command1, Command command2)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		CombinationAutonomousStation combinationStation = new CombinationAutonomousStation();
		Command newCommand1 = makeANewCommand(command1);
		Command newCommand2 = makeANewCommand(command2);
		combinationStation.addSequential(newCommand1);
		combinationStation.commands.add(newCommand1);
		combinationStation.addSequential(newCommand2);
		combinationStation.commands.add(newCommand2);
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
