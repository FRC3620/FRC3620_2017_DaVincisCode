package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearRightRedCommand extends CommandGroup {

    public AutoGearRightRedCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	addSequential(new AutoShiftDownCommand());
    	addParallel(new CloseGearSupportCommand(), .25);
    	addSequential(new AutomatedMoveCommand(65, .85));
    	addSequential(new AutomatedTurnCommand(-62));
    	addSequential(new ScoreGearFromPointSenecaCommand(),4);
    	
    	

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
