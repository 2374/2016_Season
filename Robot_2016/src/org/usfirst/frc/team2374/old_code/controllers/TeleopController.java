package org.usfirst.frc.team2374.old_code.controllers;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2374.old_code.Robot;

public class TeleopController extends RobotController {
	
    public TeleopController(Robot robot) {
        super(robot);
    }

    public double quadraticScale(double value){
    	return value*Math.abs(value);
    }
    
    @Override
    protected void onStart() {
        delay(5, () -> {
            //do something
        });
    }
    boolean firstPneumaticsPressed = myRobot.joystick1.getRawAxis(2) != 0 || myRobot.joystick1.getRawAxis(3) != 0 || myRobot.joystick1.getRawButton(5) || myRobot.joystick1.getRawButton(6);
    boolean secondControllerActive;
    @Override
    protected void onUpdate() {
        //Control wheel speeds
    	/*if(myRobot.joystick1.getRawAxis(1) > -0.05 && myRobot.joystick1.getRawAxis(1) < 0.05){
    		myRobot.drivetrain.setSpeed(0,0);
    	}if(myRobot.joystick1.getRawAxis(5) > -0.05 && myRobot.joystick1.getRawAxis(5) < 0.05){
    		myRobot.drivetrain.setSpeed(0,0);
    	}*/
    	myRobot.drivetrain.setSpeed(quadraticScale(-myRobot.joystick1.getRawAxis(1)),quadraticScale(-myRobot.joystick1.getRawAxis(5)));//Ian wanted to reverse robot direction, make sure I did this right
        //Control solenoids
        boolean frontLeftSol = myRobot.joystick1.getRawAxis(2) != 0;
        boolean frontRightSol = myRobot.joystick1.getRawAxis(3) != 0;
        boolean backLeftSol = myRobot.joystick1.getRawButton(1);
        boolean backRightSol = myRobot.joystick1.getRawButton(3); //The buttons are acting strangely, look into it.
        if(firstPneumaticsPressed) {
        	secondControllerActive=false;
        	frontLeftSol = myRobot.joystick1.getRawAxis(2) != 0;
        	frontRightSol = myRobot.joystick1.getRawAxis(3) != 0;
        	backLeftSol = myRobot.joystick1.getRawButton(1);
        	backRightSol = myRobot.joystick1.getRawButton(4);
        }
        else{
        	secondControllerActive=true;
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(1)){//Figure out actual Solenoid configs.
        	if(myRobot.joystick1.getPOV(0)==0){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        	if(myRobot.joystick1.getPOV(0)==90){
        		myRobot.drivetrain.setSolenoids(2);
        	}
        	if(myRobot.joystick1.getPOV(0)==180){
        		myRobot.drivetrain.setSolenoids(3);
        	}
        	if(myRobot.joystick1.getPOV(0)==270){
        		myRobot.drivetrain.setSolenoids(4);
        	}
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(2)){
        	if(myRobot.joystick1.getPOV(0)==0){
        		myRobot.drivetrain.setSolenoids(0);
        	}
        	if(myRobot.joystick1.getPOV(0)==90){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        	if(myRobot.joystick1.getPOV(0)!=180){
        		myRobot.drivetrain.setSolenoids(2);
        	}
        	if(myRobot.joystick1.getPOV(0)!=270){
        		myRobot.drivetrain.setSolenoids(3);
        	}
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(3)){
        	if(myRobot.joystick1.getPOV(0)==0){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        	if(myRobot.joystick1.getPOV(0)==90){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        	if(myRobot.joystick1.getPOV(0)==180){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        	if(myRobot.joystick1.getPOV(0)==270){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(4)){
        	if(myRobot.joystick1.getPOV(0)==0){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        	if(myRobot.joystick1.getPOV(0)==90){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        	if(myRobot.joystick1.getPOV(0)==180){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        	if(myRobot.joystick1.getPOV(0)==270){
        		myRobot.drivetrain.setSolenoids(1);
        	}
        }
        myRobot.drivetrain.setSolenoids(frontLeftSol ? Value.kForward : Value.kReverse, frontRightSol ? Value.kForward : Value.kReverse,backLeftSol ? Value.kForward : Value.kReverse,backRightSol ? Value.kForward : Value.kReverse);
        //Control shooter
        myRobot.angledShooter.update(2, myRobot.joystick1.getRawButton(5), myRobot.joystick1.getRawButton(6));
        //Control intake
        myRobot.intake.update(myRobot.joystick1.getRawButton(6), myRobot.joystick1.getRawButton(8));
        //Control manipulator
        myRobot.manipulator.update(myRobot.joystick1.getRawButton(2), myRobot.joystick1.getRawButton(4));
        if(myRobot.accelerometer.getZ()>.5){
        	myRobot.joystick1.setRumble(RumbleType.kLeftRumble,1);
        }  
        else{
        	myRobot.joystick1.setRumble(RumbleType.kLeftRumble,0);
        }
        SmartDashboard.putNumber("Left Encoder Speed",myRobot.drivetrain.getLeftRate());
        SmartDashboard.putNumber("Right Encoder Speed", myRobot.drivetrain.getRightRate());
        SmartDashboard.putNumber("Wheel Encoder Speed",myRobot.angledShooter.getRate());
        SmartDashboard.putNumber("X Acceleration", myRobot.accelerometer.getX());
        SmartDashboard.putNumber("Y Acceleration", myRobot.accelerometer.getY());
        SmartDashboard.putNumber("Z Acceleration", myRobot.accelerometer.getZ());
    }

    @Override
    protected void onFinish() {
        myRobot.drivetrain.setSolenoids(Value.kOff, Value.kOff, Value.kOff, Value.kOff);
    }

    @Override
    protected boolean shouldFinish() {
        return myRobot.isDisabled() || !myRobot.isOperatorControl();
    }
}