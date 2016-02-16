package org.usfirst.frc.team2374.new_code.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.new_code.Command;
import org.usfirst.frc.team2374.new_code.Component;
import org.usfirst.frc.team2374.new_code.Robot;

public class ContinueToCrossObstacle extends Command {

	@Override
	public List<Component> requires() {
		return Arrays.asList(Robot.drivetrain);
	}

	@Override
	public boolean isFinished() {
		if (Robot.positionTracker.position.z == 0) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		Robot.drivetrain.setSpeed(1, 1);
		// TODO Auto-generated method stub

	}
}

}
