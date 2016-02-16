package org.usfirst.frc.team2374.old_code.controllers;

import org.usfirst.frc.team2374.old_code.Robot;

public class DisabledController extends RobotController {

    public DisabledController(Robot robot) {
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
        return myRobot.isEnabled();
    }
}
