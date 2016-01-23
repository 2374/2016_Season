package org.usfirst.frc.team2374.robot.controllers;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import java.util.function.UnaryOperator;
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
        UnaryOperator<Double> editSpeed = d -> {
            if (Math.abs(d) < .2) {
                return 0.;
            }
            d -= .2 * Math.signum(d);
            d *= d * d * 1.95;
            return 0.;
        };
        double leftSpeed = myRobot.joystick.getRawAxis(1);
        double rightSpeed = myRobot.joystick.getRawAxis(5);
        myRobot.drivetrain.setSpeed(editSpeed.apply(leftSpeed), editSpeed.apply(rightSpeed));
        //Control solenoids
        boolean frontSol = myRobot.joystick.getRawAxis(3) != 0;
        boolean backSol = myRobot.joystick.getRawAxis(2) != 0;
        myRobot.drivetrain.setSolenoids(frontSol ? Value.kForward : Value.kReverse, backSol ? Value.kForward : Value.kReverse);
        //Control shooter
        myRobot.angledShooter.update(2, myRobot.joystick.getRawButton(5), myRobot.joystick.getRawButton(6));
        //Control intake
        myRobot.intake.update(myRobot.joystick.getRawButton(1), myRobot.joystick.getRawButton(3));
        //Control chainlift
        myRobot.chainlift.update(myRobot.joystick.getRawButton(2), myRobot.joystick.getRawButton(4));
    }

    @Override
    protected void onFinish() {
        myRobot.drivetrain.setSolenoids(Value.kOff, Value.kOff);
    }

    @Override
    protected boolean shouldFinish() {
        return myRobot.isDisabled() || !myRobot.isOperatorControl();
    }
}
