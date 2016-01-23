package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Robot;

public class TestController extends RobotController {

    public TestController(Robot robot) {
        super(robot);
    }

    @Override
    protected void onStart() {
    }

    @Override
    protected void onUpdate() {
    }

    @Override
    protected void onFinish() {
    }

    @Override
    protected boolean shouldFinish() {
        return myRobot.isDisabled() || !myRobot.isTest();
    }
}
