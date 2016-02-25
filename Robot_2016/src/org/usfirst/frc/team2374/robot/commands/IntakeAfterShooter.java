package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;


public class IntakeAfterShooter extends TimedCommand{

	public IntakeAfterShooter() {
		super(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Component> requires() {
		// TODO Auto-generated method stub
		return Arrays.asList(Robot.intake);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Robot.intake.setSpeed(1);
	}
	
}
