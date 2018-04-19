package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedAuto extends CommandGroup {

    public TimedAuto() { // currently time based
    	addParallel(new GoToSwitch()); // move the arm/wrist as the robot moves
    	addParallel(new SetIntake(-0.5));
   	
    	addSequential(new DriveForward(0.5, 0.3));
    	//addSequential(new  TurnToAngleFMS(45,  0.2,  0.5,  3));
    	addSequential(new TurnToAngleTimeFMS(0.4, 0.6)); // turn
    	addSequential(new DriveForwardFMS(0.7, 0.80, 0.2));
    	//addSequential(new  TurnToAngleFMS(-47,  0.2,  0.5,  3));
    	addSequential(new TurnToAngleTimeFMS(0.4, -0.45)); // turn
    	addSequential(new  DriveForwardFMS(0.3, 1, .1));
    	/*
    	addSequential(new DriveForward(0.25, 0.25, 4)); //1s
    	addSequential(new TurnToAngleFMS(43, RobotMap.TURN_SPEED, 2));
    	addSequential(new DriveForwardFMS(0.25, 0.25, 74.5, 12)); //1s
    	addSequential(new TurnToAngleFMS(-37, RobotMap.TURN_SPEED, 2));
    	//addSequential(new WaitForArmWrist()); // make sure the GoToSwitch has finished
    	addSequential(new DriveForward(0.3, 0.3, 80)); //1.2s
    	addSequential(new DriveForward(0.3, 0.3, 80)); //1.2s
    	*/
    	addSequential(new Wait(0.25)); // wait for the robot to stop
    	addSequential(new ReleaseBox());    
    	//addSequential(new TurnToAngleFMS(-10, RobotMap.TURN_SPEED, 2));
    	
    	addSequential(new DriveForward(-0.5, 0.5));
    	addParallel(new GoToGround());
    	addSequential(new TurnToAngleTimeFMS(0.55, 0.6)); // turn
    	addSequential(new DriveForwardFMS(-0.75, 0.725, 0.0));
    	addSequential(new TurnToAngleTimeFMS(0.5, -0.6)); // turn
    	addSequential(new DriveForward(-0.5, 0.3));
    	addSequential(new Wait(1.5)); // wait for the robot to stop
    	addSequential(new ClampOpen()); // open clamp
    	addParallel(new SetIntake(-1));
    	addSequential(new DriveForward(0.3, 2));
    	addSequential(new IntakeBox());
    	addParallel(new SetIntake(-0.5));
    	
    }
}