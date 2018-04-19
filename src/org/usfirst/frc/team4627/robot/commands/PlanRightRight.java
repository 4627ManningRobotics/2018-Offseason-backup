package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlanRightRight extends CommandGroup {

    public PlanRightRight() {
    	//addParallel(new GoToScale());

    	super.addParallel(new GoToSwitch());
    	super.addParallel(new SetIntake(-0.3));
    	
    	super.addSequential(new TurnToAngle(5, 0.3, -0.1, 3));
    	super.addSequential(new DriveForward(0.3, 0.3, 5));
    	super.addSequential(new TurnToAngle(-90, 0.3, 3));
    	super.addSequential(new DriveForward(0.3, 0.3, 1));
    	super.addSequential(new ReleaseBox());
    	
    	super.addSequential(new ReleaseBox());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
