package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriverControls extends Command {

	public boolean isTurning, wasTurning;
	
    public DriverControls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.wasTurning = this.isTurning = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		double triggerVal = Robot.oi.getDriverRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.oi.getDriverRawAxis(RobotMap.LEFT_TRIGGER);
    		double stick = Robot.oi.getDriverRawAxis(RobotMap.LEFT_STICK_X) * RobotMap.TURNING_RATE;
    		
    		//if(Robot.driveTrain.isSmoothDriving) {
    			this.isTurning = (stick >= 0.1) || (stick <= -0.1);
    		
    			if(this.isTurning != this.wasTurning) {// if it changed to turning
    				if(this.isTurning) {
    					Robot.driveTrain.setRampRate(0); // turn off ramp rate
    				}else {
    					Robot.driveTrain.setRampRate(RobotMap.RAMP_RATE); // turn on ramp rate
    				}
    			}
    		
    			this.wasTurning = this.isTurning;
    		/*}else {
    			if(Robot.driveTrain.isSmoothDriving != Robot.driveTrain.wasSmooth) {
    				if(Robot.driveTrain.isSmoothDriving) {
    					Robot.driveTrain.setRampRate(0); // turn off ramp rate
    				}else {
    					Robot.driveTrain.setRampRate(RobotMap.RAMP_RATE); // turn on ramp rate
    				}
    			}
    			Robot.driveTrain.wasSmooth = Robot.driveTrain.isSmoothDriving;
    		}
    		*/
    			
    			if(triggerVal > RobotMap.DRIVER_MAX_SPEED) {
    				triggerVal = RobotMap.DRIVER_MAX_SPEED;
    			}else if(triggerVal < -RobotMap.DRIVER_MAX_SPEED) {
    				triggerVal = - RobotMap.DRIVER_MAX_SPEED;
    			}
    		Robot.driveTrain.setLeftMotor(triggerVal + stick);
    		Robot.driveTrain.setRightMotor(triggerVal - stick);
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
