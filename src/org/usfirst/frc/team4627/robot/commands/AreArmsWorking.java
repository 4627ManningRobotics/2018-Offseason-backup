package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AreArmsWorking extends Command {

	public static boolean isWorking;
	private boolean isDone;
	private short check;
	private final double TIMEOUT = 4.0; // seconds before its counted as a failure
	
    public AreArmsWorking() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super.requires(Robot.rightArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	AreArmsWorking.isWorking = false;
    	this.isDone = false;
    	this.check = 0;
    	Robot.rightArm.setSetpoint(RobotMap.ARMS_SWITCH);
    	this.check++;
    	super.setTimeout(this.TIMEOUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(this.check) {
    		case 0:
    	    	Robot.rightArm.setSetpoint(RobotMap.ARMS_SWITCH);
    	    	this.check++;
    	    	super.setTimeout(this.TIMEOUT);
    	    	break;
    		case 1:
    			if(Robot.rightArm.onTarget()){
    				this.check++;
    			}
    			break;
    		case 2:
    	    	Robot.rightArm.setSetpoint(RobotMap.ARMS_GROUND);
    	    	this.check++;
    	    	super.setTimeout(this.TIMEOUT);
    	    	break;
    		case 3:
    			if(Robot.rightArm.onTarget()){
    				this.check++;
    				AreArmsWorking.isWorking = true;
    				this.isDone = true;
    			}
    			break;
    	}
    	if(super.isTimedOut()){
    		AreArmsWorking.isWorking = false;
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
    	AreArmsWorking.isWorking = false;
    }
}
