package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.math.MathUtil;

import edu.wpi.first.wpilibj.Encoder;

public class TurnCommand extends Command {

    private final double turnTo;
    
    public TurnCommand(double turnTo) {
        this.turnTo = turnTo;
    }

    @Override
    public boolean isFinished() {
        return MathUtil.angleNear(turnTo, Robot.positionTracker.direction(), 0.174);
    }

    @Override
    public List<Component> requires() {
        return Arrays.asList(Robot.drivetrain);
    }

    double prevError;

    double motorVoltage = 0;
    double integral;
    double maxRate = 3000;
    
    public void PIDRight(double targetSpeed) {
    	Encoder encoder = Robot.drivetrain.getRightEncoder();
        double error = targetSpeed * maxRate - encoder.getRate();
        double P = 0.005;
        double I = 0.00005;
        double D = -0.005;
        if (Math.abs(error) >= 0) {
            motorVoltage += (error * P) + (error - prevError) * D
                    + (integral * I);
            if (motorVoltage > maxRate) {
                motorVoltage = maxRate;
            } else if (motorVoltage < -maxRate) {
                motorVoltage = -maxRate;
            }
            Robot.drivetrain.setSpeed(motorVoltage / maxRate,0);
            integral += error;
            prevError = error;
        }
    }
    
    public void PIDLeft(double targetSpeed) {
    	Encoder encoder = Robot.drivetrain.getLeftEncoder();
        double error = targetSpeed * maxRate - encoder.getRate();
        double P = 0.005;
        double I = 0.00005;
        double D = -0.005;
        if (Math.abs(error) >= 0) {
            motorVoltage += (error * P) + (error - prevError) * D
                    + (integral * I);
            if (motorVoltage > maxRate) {
                motorVoltage = maxRate;
            } else if (motorVoltage < -maxRate) {
                motorVoltage = -maxRate;
            }
            Robot.drivetrain.setSpeed(0,motorVoltage / maxRate);
            integral += error;
            prevError = error;
        }
    }

    public void resetPid() {
        prevError = 0;
        integral = 0;
        motorVoltage = 0;
    }

    @Override
    public void update() {
        if (MathUtil.turnDirection(Robot.positionTracker.direction(), turnTo)) {
            PIDRight(1);
        } else {
            PIDLeft(1);
        }
    }
}
