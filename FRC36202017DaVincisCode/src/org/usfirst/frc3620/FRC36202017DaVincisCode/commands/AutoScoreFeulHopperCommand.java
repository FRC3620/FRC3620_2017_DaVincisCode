package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScoreFeulHopperCommand extends CommandGroup {

    public AutoScoreFeulHopperCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

    	addParallel(new AutoRunShooterCommand(3100));
    	addSequential(new AutonomousDoNothingCommand(), .5);
    	addSequential(new FeedShooterCommand(),3);
    	addSequential(new AutomatedMoveTimedCommand(.5, -.9));
    	
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
