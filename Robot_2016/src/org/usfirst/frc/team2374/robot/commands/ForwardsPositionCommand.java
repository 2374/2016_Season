package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;

public class ForwardsPositionCommand extends Command {

	final public double ENCODER_FEET_PER_COUNT = 360 * 2.0943951023932;

	public double target;
	// public boolean isX;

	Encoder LeftEncoder = Robot.drivetrain.getLeftEncoder();
	Encoder RightEncoder = Robot.drivetrain.getRightEncoder();

	public ForwardsPositionCommand(double t) {
		target = t;
		// isX = x;
	}

	@Override
	public boolean isFinished() {
		return LeftEncoder.getDistance() >= target
				&& RightEncoder.getDistance() >= target;
	}

	@Override
	public List<Component> requires() {
		return Arrays.asList(Robot.drivetrain);
	}

	@Override
	public void start() {
		super.start();
		LeftEncoder.reset();
		RightEncoder.reset();
		LeftEncoder.setDistancePerPulse(ENCODER_FEET_PER_COUNT);// converts from
																// encoder
																// counts to
																// wheel
																// revolutions
																// to feet
		RightEncoder.setDistancePerPulse(ENCODER_FEET_PER_COUNT);
	}

	@Override
	public void update() {
		Robot.drivetrain.setSpeed(1, 1);

	}

}
