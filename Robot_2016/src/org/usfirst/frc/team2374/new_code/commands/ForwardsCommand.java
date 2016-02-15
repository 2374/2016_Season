package org.usfirst.frc.team2374.new_code.commands;

import java.util.Arrays;
import java.util.List;
import org.usfirst.frc.team2374.new_code.Component;
import org.usfirst.frc.team2374.new_code.Robot;

public class ForwardsCommand extends TimedCommand {

    public ForwardsCommand(double time) {
        super(time);
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
