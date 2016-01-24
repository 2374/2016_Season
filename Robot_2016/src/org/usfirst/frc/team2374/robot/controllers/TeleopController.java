package org.usfirst.frc.team2374.robot.controllers;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import org.usfirst.frc.team2374.robot.Robot;

public class TeleopController extends RobotController {

    public TeleopController(Robot robot) {
        super(robot);
    }

    @Override
    protected void onStart() {
        delay(5, () -> {
            //do something
        });
    }

    @Override
    protected void onUpdate() {
        //Control wheel speeds
        myRobot.drivetrain.setSpeed(myRobot.joystick.getRawAxis(1), myRobot.joystick.getRawAxis(5));
        //Control solenoids
        boolean frontLeftSol = myRobot.joystick.getRawAxis(2) != 0;
        boolean frontRightSol = myRobot.joystick.getRawAxis(3) != 0;
        boolean backLeftSol = myRobot.joystick.getRawButton(2);
        boolean backRightSol = myRobot.joystick.getRawButton(1); //The buttons are acting strangely, look into it.
        myRobot.drivetrain.setSolenoids(frontLeftSol ? Value.kForward : Value.kReverse, frontRightSol ? Value.kForward : Value.kReverse,backLeftSol ? Value.kForward : Value.kReverse,backRightSol ? Value.kForward : Value.kReverse);
        //Control shooter
        myRobot.angledShooter.update(2, myRobot.joystick.getRawButton(5), myRobot.joystick.getRawButton(6));
        //Control intake
        myRobot.intake.update(myRobot.joystick.getRawButton(1), myRobot.joystick.getRawButton(3));
        //Control chainlift
        myRobot.chainlift.update(myRobot.joystick.getRawButton(2), myRobot.joystick.getRawButton(4));
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