package org.usfirst.frc.team2374.robot.controllers;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import org.usfirst.frc.team2374.robot.Robot;

public class TeleopController extends RobotController {
	int state=0;
    public TeleopController(Robot robot) {
        super(robot);
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
        myRobot.drivetrain.setSpeed(myRobot.joystick1.getRawAxis(1), myRobot.joystick1.getRawAxis(5));
        //Control solenoids
        boolean frontLeftSol = myRobot.joystick1.getRawAxis(2) != 0;
        boolean frontRightSol = myRobot.joystick1.getRawAxis(3) != 0;
        boolean backLeftSol = myRobot.joystick1.getRawButton(5);
        boolean backRightSol = myRobot.joystick1.getRawButton(6); //The buttons are acting strangely, look into it.
        if(firstPneumaticsPressed) {
        	secondControllerActive=false;
        	frontLeftSol = myRobot.joystick1.getRawAxis(2) != 0;
        	frontRightSol = myRobot.joystick1.getRawAxis(3) != 0;
        	backLeftSol = myRobot.joystick1.getRawButton(5);
        	backRightSol = myRobot.joystick1.getRawButton(6);
        }
        else{
        	secondControllerActive=true;
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(10)){
        	state=0;
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(1)){
        	state=1;
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(2)){
        	state=2;
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(3)){
        	state=3;
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(4)){
        	state=4;
        }
        if(secondControllerActive && myRobot.joystick2.getRawButton(9)){
        	state=5;
        }
        	
        switch (state){
        case 0: myRobot.drivetrain.setSolenoids(Value.kReverse, Value.kReverse, Value.kReverse, Value.kReverse);
        break;
        case 1: myRobot.drivetrain.setSolenoids(Value.kReverse, Value.kReverse, Value.kForward, Value.kForward);
        break;
        case 2: myRobot.drivetrain.setSolenoids(Value.kForward, Value.kForward, Value.kReverse, Value.kReverse);
        break;
        case 3: myRobot.drivetrain.setSolenoids(Value.kForward, Value.kForward, Value.kForward, Value.kForward);
        break;
        case 4: myRobot.drivetrain.setSolenoids(Value.kForward, Value.kReverse, Value.kReverse, Value.kForward);
        break;
        case 5: myRobot.drivetrain.setSolenoids(Value.kReverse, Value.kForward, Value.kForward, Value.kReverse);
        break;
        }
        myRobot.drivetrain.setSolenoids(frontLeftSol ? Value.kForward : Value.kReverse, frontRightSol ? Value.kForward : Value.kReverse,backLeftSol ? Value.kForward : Value.kReverse,backRightSol ? Value.kForward : Value.kReverse);
        //Control shooter
        myRobot.angledShooter.update(2, myRobot.joystick1.getRawButton(5), myRobot.joystick1.getRawButton(6));
        //Control intake
        myRobot.intake.update(myRobot.joystick1.getRawButton(1), myRobot.joystick1.getRawButton(3));
        //Control chainlift
        myRobot.chainlift.update(myRobot.joystick1.getRawButton(2), myRobot.joystick1.getRawButton(4));
   
        
        	
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