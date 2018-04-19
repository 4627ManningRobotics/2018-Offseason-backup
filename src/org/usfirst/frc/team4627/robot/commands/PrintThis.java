package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintThis extends Command {

	private String s;
	
	public PrintThis(String s) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		this.s = s;
    }
	
    public PrintThis(boolean didSucceed, String success, String failure) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if(didSucceed) {
    		this.s = success;
    	}else {
    		this.s = failure;
    	}
    }
    
    public PrintThis(boolean didSucceed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if(didSucceed) {
    		this.s = "Success!";
    	}else {
    		this.s = "Failure";
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(this.s);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
