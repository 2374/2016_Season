package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;
import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;

public class CrossObstaclePart1Command extends Command {

    @Override
    public boolean isFinished() {
        return Robot.positionTracker.position.z != 0;
    }

    @Override
    public List<Component> requires() {
        return Arrays.asList(Robot.drivetrain);
    }

    @Override
    public void update() {
        Robot.drivetrain.setSpeed(1, 1);
    }
}
