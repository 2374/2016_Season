package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;
import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.math.MathUtil;

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

    @Override
    public void update() {
        if (MathUtil.turnDirection(Robot.positionTracker.direction(), turnTo)) {
            Robot.drivetrain.setSpeed(1, 0);
        } else {
            Robot.drivetrain.setSpeed(0, 1);
        }
    }
}
