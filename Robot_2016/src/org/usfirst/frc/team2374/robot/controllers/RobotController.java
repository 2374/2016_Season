package org.usfirst.frc.team2374.robot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2374.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public abstract class RobotController {

    private final Map<Runnable, Double> toRun = new HashMap<>();
    protected Robot myRobot;

    public RobotController(Robot robot) {
        myRobot = robot;
    }

    protected void delay(double time, Runnable r) {
        toRun.put(r, Timer.getFPGATimestamp() + time);
    }

    public void run() {
        onStart();
        while (!shouldFinish()) {
            for (Runnable r : toRun.keySet()) {
                if (Timer.getFPGATimestamp() > toRun.get(r)) {
                    r.run();
                    toRun.remove(r);
                }
            }
            onUpdate();
            Timer.delay(.01);
        }
        onFinish();
    }

    protected abstract void onStart();

    protected abstract void onUpdate();

    protected abstract void onFinish();

    protected abstract boolean shouldFinish();
}
