package org.usfirst.frc.team2374.new_code.commands;

import java.util.Arrays;
import java.util.List;
import org.usfirst.frc.team2374.new_code.Command;
import org.usfirst.frc.team2374.new_code.Component;
import org.usfirst.frc.team2374.new_code.Robot;
import org.usfirst.frc.team2374.new_code.events.Input;

public class TankDriveCommand extends Command {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<Component> requires() {
        return Arrays.asList(Robot.drivetrain);
    }

    public double quadraticScale(double value) {
        return value * Math.abs(value);
    }

    @Override
    public void update() {
        //This code was copied almost directly from the old TeleopController

        Robot.drivetrain.setSpeed(quadraticScale(-Input.getAxis(1)), quadraticScale(-Input.getAxis(5))); //Ian wanted to reverse robot direction, make sure I did this right
    }
}
