package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IsDriveTrainWorking extends Command {

	public static boolean isWorking;
	private boolean isDone;
	private final double TIMEOUT = 1.5; // seconds before its counted as a failure
	
    public IsDriveTrainWorking() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super.requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.setRightMotor(0.5);
    	Robot.driveTrain.setLeftMotor(0.5);
    	IsDriveTrainWorking.isWorking = false;
    	this.isDone = false;
    	super.setTimeout(this.TIMEOUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	if(Robot.driveTrain.getDistance() >= 10) { // has moved at all
    		IsDriveTrainWorking.isWorking = true;
    		this.isDone = true;
    	}
    	
    	if(super.isTimedOut()){
    		IsDriveTrainWorking.isWorking = false;
    		this.isDone = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
