package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SystemsCheck extends CommandGroup {

    public SystemsCheck() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	super.addParallel(new SetWrist(RobotMap.WRIST_SWITCH)); // get the wrist out of the way
    	
    	super.addSequential(new PrintThis("Checking Arms..."));
    	super.addSequential(new AreArmsWorking()); // moves arms to set-points and then check to see if they are there 
    	super.addSequential(new PrintThis(AreArmsWorking.isWorking));
    	super.addSequential(new PrintThis("Cheching Wrist..."));
    	super.addSequential(new IsWristWorking()); // moves arms to set-points and then check to see if they are there 
    	super.addSequential(new PrintThis(IsWristWorking.isWorking));
    	super.addSequential(new PrintThis("Checking Drive Train..."));
    	super.addSequential(new IsDriveTrainWorking());
    	super.addSequential(new PrintThis(IsDriveTrainWorking.isWorking));
    	super.addSequential(new PrintThis("Checking Gyro..."));
    	super.addSequential(new IsGyroWorking());
    	super.addSequential(new PrintThis(IsGyroWorking.isWorking));
    	
    	super.addSequential(new GoToStow());
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
