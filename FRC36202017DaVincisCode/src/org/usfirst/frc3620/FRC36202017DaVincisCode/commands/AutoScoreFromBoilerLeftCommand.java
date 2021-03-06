package org.usfirst.frc3620.FRC36202017DaVincisCode.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScoreFromBoilerLeftCommand extends CommandGroup {

    public AutoScoreFromBoilerLeftCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
    	addParallel(new RunShooterCommand());
    	addSequential(new AutoBackUpFromPegCommand());
    	addSequential(new AutomatedTurnCommand(-13.25));
    	addSequential(new FeedShooterCommand());
    
    	
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
