package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Controller;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.commands.CrossObstaclePart1Command;
import org.usfirst.frc.team2374.robot.commands.CrossObstaclePart2Command;
import org.usfirst.frc.team2374.robot.commands.ForwardsCommand;
import org.usfirst.frc.team2374.robot.commands.ForwardsPositionCommand;
import org.usfirst.frc.team2374.robot.commands.IntakeAfterShooter;
import org.usfirst.frc.team2374.robot.commands.PistonAutonomousCommand;
import org.usfirst.frc.team2374.robot.commands.ShooterAutonomous;
import org.usfirst.frc.team2374.robot.commands.TurnCommand;

public class AutonomousController extends Controller {
	static int log = 0;
	int autoCase;

	public static Command moveTo(double goal_x, double goal_y) {
		double xP = Robot.positionTracker.position.x;
		double yP = Robot.positionTracker.position.y;

		Command turnToCorrectY = turn(yP < goal_y ? 90 : -90);
		Command moveToCorrectY = new ForwardsPositionCommand(goal_y);
		Command turnToCorrectX = turn(0);
		Command moveToCorrectX = new ForwardsPositionCommand(goal_x);

		turnToCorrectY.thenRun(moveToCorrectY).thenRun(turnToCorrectX)
				.thenRun(moveToCorrectX);

		turnToCorrectY.start();

		return moveToCorrectX;
	}

	static Command turn(double turnAngle) {
		return new TurnCommand(turnAngle);
	}

	/*
	 * public double getGoalX() {// these autocases should correspond with
	 * vertical // distance between robot and tower, look into // it now because
	 * I know you won't do it later // DISTANCES ARE IN FEET autoCase = (int)
	 * Robot.autoChooserPositions.getSelected(); if (autoCase == 5)// position 2
	 * (see frc website) return 239.0 / 24.0; else if (autoCase == 6)// position
	 * 3 return 239.0 / 24.0; else if (autoCase == 7)// position 4 return 239.0
	 * / 24.0; else if (autoCase == 8)// position 5 return 239.0 / 24.0; else
	 * return 0; }
	 * 
	 * public double getGoalY() {// these autocases should correspond with //
	 * horizontal distance between the obstacles and // the tower // DISTANCES
	 * ARE IN FEET autoCase = (int) Robot.autoChooserPositions.getSelected(); if
	 * (autoCase == 5)// position 2 (see frc website) return -4.2; else if
	 * (autoCase == 6)// position 3 return 0; else if (autoCase == 7)// position
	 * 4 return 4.2; else if (autoCase == 8)// position 5 return 8.4; else
	 * return 0; }
	 */
	@Override
	public void start() {
		if (autoCaseSelected() == 13) {
			Command nothing = new ForwardsCommand(0);
			nothing.finish();
		} else {
			log++;
			// SmartDashboard.putString("Auto", ""+log);
			Robot.positionTracker.reset();
			// new ForwardsCommand(2).start();
			// we need to initialize the accelerometer and gyro
			// Go forwards
			// Move to somewhere
			// Shoot
			// We need to implement autocases, solenoid command, and over
			// obstacle
			// command
			Command forwards = new ForwardsCommand(2);
			Command piston = new PistonAutonomousCommand(autoCaseSelected());
			Command pistonstop = new PistonAutonomousCommand(0);
			Command crossObstacle1 = new CrossObstaclePart1Command();
			Command crossObstacle2 = new CrossObstaclePart2Command();
			Command intake = new IntakeAfterShooter();
			Command shoot = new ShooterAutonomous();// see the shooter command;
													// it's
													// important and I know it's
													// the
													// only way you'll remember
													// to
													// check, look into it now
													// because I know you won't
													// do
													// it later
			// Command moveToSomewhere = moveTo(getGoalX(), getGoalY());

			// forwards.thenRun(moveToSomewhere).thenRun(shoot);
			forwards.thenRun(crossObstacle1).thenRun(crossObstacle2);
			/* .thenRun(moveToSomewhere).thenRun(shoot).thenRun(intake); */

			crossObstacle2.thenRun(pistonstop);
			piston.start();
			forwards.start();
		}
	}

	public int autoCaseSelected() {// these correspond to the piston
									// configurations, look into them now
									// because they're important and I know you
									// won't do it later
		return (int) Robot.autoChooserObstacles.getSelected();

	}
}
