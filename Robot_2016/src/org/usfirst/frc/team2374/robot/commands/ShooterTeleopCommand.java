package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterTeleopCommand extends Command {

	final double SHOOTER_SPEED = 0.6;
	
	@Override
	public boolean isFinished() {
		return false;
	}

	public boolean FirstControllerActive() {
		return (Input.JOYSTICK1.getRawButton(5))
				|| (Input.JOYSTICK1.getRawButton(6));
	}

	@Override
	public List<Component> requires() {
		return Arrays.asList(Robot.shooter);
	}
	
	@Override
	public void update() {

		if (Input.JOYSTICK1.getRawButton(5)) {
			Robot.shooter.PIDShooter(SHOOTER_SPEED);// implement PID
		} else if (Input.JOYSTICK1.getRawButton(7)) {
			Robot.shooter.PIDShooter(-SHOOTER_SPEED);// implement PID
		} else if (!FirstControllerActive() && Input.JOYSTICK2.getRawButton(5)) {
			Robot.shooter.PIDShooter(SHOOTER_SPEED);// implement PID
			/*SmartDashboard.putString("controller 2 Raw Button 5",
					"Shooter Active-Forwards");*/
		} else if (!FirstControllerActive() && Input.JOYSTICK2.getRawButton(6)) {
			Robot.shooter.PIDShooter(-SHOOTER_SPEED);// implement PID
			/*SmartDashboard.putString("controller 2 Raw Button 6",
					"Shooter Active-Reverse");*/
		} else {
			Robot.shooter.PIDShooter(0);
			//SmartDashboard.putString("Should shooter be active?", "NO");
		}
	}
}