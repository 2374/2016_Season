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
		Command piston;
		Command forwards;
		if (autoCaseSelected() == 13) {
			Command nothing = new ForwardsCommand(0);
			nothing.finish();
		} else if(autoCaseSelected()==0){
			forwards = new ForwardsCommand(3.5);
			piston = new PistonAutonomousCommand(0);
			piston.start();
			forwards.start();
		} else if (autoCaseSelected()==12){
			forwards = new ForwardsCommand(3.5);
			piston = new PistonAutonomousCommand(12);
			piston.start();
			forwards.start();
		}

		//piston.start();
		//forwards.start();
	}

	public int autoCaseSelected() {
		return (int) Robot.autoChooserObstacles.getSelected();

	}
}
