package org.usfirst.frc.team2374.robot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2374.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public abstract class RobotController {
    private final Map<Runnable, Double> toRun = new HashMap<>();
    protected Robot myRobot;
    private double prevTime;
    private double currentTime;

    public RobotController(Robot robot) {
        myRobot = robot;
        myRobot.robotController = this;
    }

    public void delay(double time, Runnable r) {
        toRun.put(r, Timer.getFPGATimestamp() + time);
    }
    
    public double deltaTime() {
    	return currentTime - prevTime;
    }

    public void run() {
    	prevTime = Timer.getFPGATimestamp();
        onStart();
        while (!shouldFinish()) {
        	currentTime = Timer.getFPGATimestamp();
            for (Runnable r : toRun.keySet()) {
                if (Timer.getFPGATimestamp() > toRun.get(r)) {
                    r.run();
                    toRun.remove(r);
                }
            }
            onUpdate();
            prevTime = Timer.getFPGATimestamp();
            Timer.delay(.01);
        }
        onFinish();
    }

    protected abstract void onStart();

    protected abstract void onUpdate();

    protected abstract void onFinish();

    protected abstract boolean shouldFinish();
}
