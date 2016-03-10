package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;

public class CrossObstaclePart2Command extends Command {

	@Override
	public boolean isFinished() {
		return Robot.positionTracker.acceleration.y <= 10
				|| !Robot.robot.isAutonomous();
	}

	@Override
	public List<Component> requires() {
		return Arrays.asList(Robot.drivetrain);
	}

	@Override
	public void update() {
		Robot.drivetrain.setSpeed(1, 1);
	}

	@Override
	public void start() {

		// StringWriter sw = new StringWriter();
		// PrintWriter pw = new PrintWriter(sw);
		// new Exception().printStackTrace(pw);
		// SmartDashboard.putString("Stack Trace", sw.toString());

		// super.start();
	}
}
