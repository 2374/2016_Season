package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

public class ShooterTeleopCommand extends Command{
	@Override
    public boolean isFinished() {
        return false;
    }

	@Override
    public List<Component> requires() {
        return Arrays.asList(Robot.shooter);
    }
  
	@Override
 	public void update () {
	 if (Input.getButton(5)){
		 Robot.shooter.setSpeed(1);//implement PID
	 }
	 if (Input.getButton(6)){
		 Robot.shooter.setSpeed(-1);//implement PID
	 }
	}
}