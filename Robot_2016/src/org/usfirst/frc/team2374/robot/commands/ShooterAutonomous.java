package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;

public class ShooterAutonomous extends Command {

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.shooter.getRate() > .55// consider changing this to return
											// null, I doubt that we want the
											// shooter to stop running in
											// autonomous
				|| !Robot.robot.isAutonomous();
	}

	@Override
	public List<Component> requires() {
		// TODO Auto-generated method stub
		return Arrays.asList(Robot.shooter);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Robot.shooter.setSpeed(0.48);
	}

}
