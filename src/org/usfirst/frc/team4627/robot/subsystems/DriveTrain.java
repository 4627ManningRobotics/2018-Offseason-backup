package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import org.usfirst.frc.team4627.robot.commands.DriverControls;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	//instantiate motor controllers
	private final TalonSRX leftMotor1 = new TalonSRX(RobotMap.LEFT_MOTOR_1);
	private final TalonSRX leftMotor2 = new TalonSRX(RobotMap.LEFT_MOTOR_2);
	private final TalonSRX rightMotor1 = new TalonSRX(RobotMap.RIGHT_MOTOR_1);
	private final TalonSRX rightMotor2 = new TalonSRX(RobotMap.RIGHT_MOTOR_2);
	
	private final Solenoid theSolenoid = new Solenoid(RobotMap.SOLENOID);
	public boolean isInHighGear = false; //, isSmoothDriving = false, wasSmooth = false;
	
	private static final Encoder leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	private static final Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);

	private double distancePerPulse = (2*RobotMap.WHEEL_DIAMETER)/(RobotMap.ENCODER_PULSES_PER_REVOLUTION/RobotMap.ENCODER_GEAR_RATIO);
	
	public DriveTrain() {
		this.leftMotor1.configOpenloopRamp(RobotMap.RAMP_RATE, 0); // configure the time it takes for the motors to reach max speed
		this.leftMotor2.configOpenloopRamp(RobotMap.RAMP_RATE, 0); 
		this.rightMotor1.configOpenloopRamp(RobotMap.RAMP_RATE, 0); 
		this.rightMotor2.configOpenloopRamp(RobotMap.RAMP_RATE, 0); 
		/*
		this.leftMotor1.configPeakCurrentLimit(RobotMap.CURRENT_LIMIT, 10);
		this.leftMotor2.configPeakCurrentLimit(RobotMap.CURRENT_LIMIT, 10);
		this.rightMotor1.configPeakCurrentLimit(RobotMap.CURRENT_LIMIT, 10);
		this.rightMotor2.configPeakCurrentLimit(RobotMap.CURRENT_LIMIT, 10);
		
		this.leftMotor1.enableCurrentLimit(true);
		this.leftMotor2.enableCurrentLimit(true);
		this.rightMotor1.enableCurrentLimit(true);
		this.rightMotor2.enableCurrentLimit(true);
		*/
		
		this.theSolenoid.set(!this.isInHighGear);
	}
	
	public void initEncoders() {
		leftEncoder.setDistancePerPulse(this.distancePerPulse);
		rightEncoder.setDistancePerPulse(this.distancePerPulse);
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public double getDistance() {
		//System.out.println(leftEncoder + " " + rightEncoder);
		double distance = (leftEncoder.getDistance() + rightEncoder.getDistance()/2);
		return distance;
	}

	public double getRightPulse() {
		return rightEncoder.getRaw();
	}
	
	public double getLeftPulse() {
		return leftEncoder.getRaw();
	}
	
    public void initDefaultCommand() {
    	super.setDefaultCommand(new DriverControls());
    }
        
    public void setLeftMotor(double motorSetting) {
    	motorSetting *= RobotMap.DRIVER_MAX_SPEED;
    	leftMotor1.set(leftMotor1.getControlMode(), motorSetting);
    	leftMotor2.set(leftMotor2.getControlMode(), motorSetting);
    }
    
    public void setRightMotor(double motorSetting) {
    	motorSetting *= RobotMap.DRIVER_MAX_SPEED;
    	rightMotor1.set(rightMotor1.getControlMode(), -motorSetting); //reverse setting 
    	rightMotor2.set(rightMotor2.getControlMode(), -motorSetting);
    }
    
    public void changeGears() {
    	this.isInHighGear = !this.isInHighGear;
    	this.theSolenoid.set(!this.isInHighGear);
    }
    
    public void setGear(boolean set) {
    	this.isInHighGear = set;
    	this.theSolenoid.set(!this.isInHighGear);
    }
    
    public void setRampRate(double rr) {
    	this.leftMotor1.configClosedloopRamp(rr, 0);
    	this.leftMotor2.configClosedloopRamp(rr, 0);
    	this.rightMotor1.configClosedloopRamp(rr, 0);
    	this.rightMotor2.configClosedloopRamp(rr, 0);
    }
}

