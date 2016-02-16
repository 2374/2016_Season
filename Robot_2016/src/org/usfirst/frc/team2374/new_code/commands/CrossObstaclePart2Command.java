package org.usfirst.frc.team2374.new_code.commands;

import java.util.Arrays;
import java.util.List;
import org.usfirst.frc.team2374.new_code.Command;
import org.usfirst.frc.team2374.new_code.Component;
import org.usfirst.frc.team2374.new_code.Robot;

public class CrossObstaclePart2Command extends Command {

    @Override
    public boolean isFinished() {
        return Robot.positionTracker.position.z == 0;
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
