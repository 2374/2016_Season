package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

import edu.wpi.first.wpilibj.Joystick.RumbleType;

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
    	if(Math.abs(value) < 0.5) return 0;//deadzone
        return value * Math.abs(value);
    }

    @Override
    public void update() {
        //This code was copied almost directly from the old TeleopController
    	if(Robot.positionTracker.acceleration.z>1){
    		Input.JOYSTICK1.setRumble(RumbleType.kLeftRumble, 1);
    		Input.JOYSTICK1.setRumble(RumbleType.kRightRumble, 1);
    	}
    	else{
    		Input.JOYSTICK1.setRumble(RumbleType.kLeftRumble, 0);
    		Input.JOYSTICK1.setRumble(RumbleType.kRightRumble, 0);
    	}
        Robot.drivetrain.setSpeed(quadraticScale(-Input.getAxis(1)), quadraticScale(-Input.getAxis(5))); //Ian wanted to reverse robot direction, make sure I did this right
    }
}
