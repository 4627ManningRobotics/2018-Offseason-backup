package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IsGyroWorking extends Command {

	public static boolean isWorking;
	private final double TIMEOUT = 2.5;// seconds before its counted as a failure
	
    public IsGyroWorking() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super.requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		IsGyroWorking.isWorking = false;
    	Robot.driveTrain.setLeftMotor(-0.3);
    	Robot.driveTrain.setRightMotor(0.3);
    	super.setTimeout(this.TIMEOUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.sensors.getGyroAngle()) >= 3) { // is it getting a reading and moving
    		IsGyroWorking.isWorking = true;
    		this.end();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isTimedOut() || IsGyroWorking.isWorking;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setLeftMotor(0);
    	Robot.driveTrain.setRightMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	IsGyroWorking.isWorking = false;
    }
}
