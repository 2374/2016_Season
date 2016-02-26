package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Controller;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.commands.CrossObstaclePart1Command;
import org.usfirst.frc.team2374.robot.commands.CrossObstaclePart2Command;
import org.usfirst.frc.team2374.robot.commands.ForwardsCommand;
import org.usfirst.frc.team2374.robot.commands.IntakeAfterShooter;
import org.usfirst.frc.team2374.robot.commands.MoveToSomewhere;
import org.usfirst.frc.team2374.robot.commands.PistonAutonomousCommand;
import org.usfirst.frc.team2374.robot.commands.ShooterAutonomous;

public class AutonomousController extends Controller {
	int autoCase;

	@Override
	public void start() {
		new ForwardsCommand(2).start();

		// we need to initialize the accelermometer and gyro
		// Go forwards
		// Move to somewhere
		// Shoot
		// We need to implement autocases, solenoid command, and over obstacle
		// command
		Command forwards = new ForwardsCommand(2);
		Command piston = new PistonAutonomousCommand(autoCaseSelected());
		Command pistonstop = new PistonAutonomousCommand(0);
		Command crossObstacle1 = new CrossObstaclePart1Command();
		Command crossObstacle2 = new CrossObstaclePart2Command();
		Command moveToSomewhere = new MoveToSomewhere(0, 0);
		Command intake = new IntakeAfterShooter();
		Command shoot = new ShooterAutonomous();

		// forwards.thenRun(moveToSomewhere).thenRun(shoot);
		piston.start();
		piston.thenRun(forwards).thenRun(crossObstacle1)
				.thenRun(crossObstacle2).thenRun(pistonstop)
				.thenRun(moveToSomewhere).thenRun(shoot).thenRun(intake);
	}

	public int autoCaseSelected() {
		return (int) Robot.autoChooser.getSelected();
	}
}
