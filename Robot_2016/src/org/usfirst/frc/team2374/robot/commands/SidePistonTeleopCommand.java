package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

public class SidePistonTeleopCommand extends Command {

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Component> requires() {
		// TODO Auto-generated method stub
		return Arrays.asList(Robot.sidePiston);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (Input.JOYSTICK1.getPOV() == 1) {
			Robot.sidePiston.setValue(1);
		} else if (Input.JOYSTICK1.getPOV() == -1) {
			Robot.sidePiston.setValue(-1);
		} else {
			Robot.sidePiston.setValue(0);
		}
	}

}
