package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Controller;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.commands.CrossObstaclePart1Command;
import org.usfirst.frc.team2374.robot.commands.CrossObstaclePart2Command;
import org.usfirst.frc.team2374.robot.commands.ForwardsCommand;
import org.usfirst.frc.team2374.robot.commands.ForwardsPositionCommand;
import org.usfirst.frc.team2374.robot.commands.IntakeAfterShooter;
import org.usfirst.frc.team2374.robot.commands.MoveToSomewhere;
import org.usfirst.frc.team2374.robot.commands.PistonAutonomousCommand;
import org.usfirst.frc.team2374.robot.commands.ShooterAutonomous;
import org.usfirst.frc.team2374.robot.commands.TurnCommand;

public class AutonomousController extends Controller {
	int autoCase;

	public static Command moveTo(double goal_x, double goal_y) {
		double xP=Robot.positionTracker.position.x;
		double yP=Robot.positionTracker.position.y;
		
		Command turnToCorrectY = turn(yP < goal_y ? 90 : -90);
		Command moveToCorrectY = new ForwardsPositionCommand(goal_y, false);
		Command turnToCorrectX = turn(0);
		Command moveToCorrectX = new ForwardsPositionCommand(goal_x, true);
		
		turnToCorrectY.thenRun(moveToCorrectY).thenRun(turnToCorrectX).thenRun(moveToCorrectX);
		
		turnToCorrectY.start();
		
		return moveToCorrectX;
	}

	static Command turn(double turnAngle){return new TurnCommand(turnAngle);
	}
	
	public double getGoalX(){
		autoCase = (int) Robot.autoChooser.getSelected();
		if (autoCase==1) return 5.0;
		else if (autoCase == 2) return 5.0;
		else if (autoCase == 3) return 5.0;
		else return 0;
	}
	
	public double getGoalY(){
		autoCase = (int) Robot.autoChooser.getSelected();
		if (autoCase==1) return 5.0;
		else if (autoCase == 2) return 5.0;
		else if (autoCase == 3) return 5.0;
		else return 0;
	}
	
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
		Command intake = new IntakeAfterShooter();
		Command shoot = new ShooterAutonomous();
		Command moveToSomewhere = moveTo(getGoalX(), getGoalY());
		
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
