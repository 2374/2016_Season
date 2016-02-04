package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousController extends RobotController {

	protected int autoCase;
	protected int turnDirection;
	private double xV, yV, zV, xP, yP, zP;

	public AutonomousController(Robot robot) {
		super(robot);
	}

	/*
	 * FOCUS ON ROUGH TERRAIN AND MOAT FOR AUTONOMOUS
	 */

	// private SendableChooser autoChooser = new SendableChooser(); //Add
	// defaults and options for rough terrain and various other obstacles

	@Override
	protected void onStart() {
		autoCase = (int) myRobot.autoChooser.getSelected();
		turnDirection = (int) myRobot.autoTurn.getSelected();
		xV = 0;
		yV = 0;
		zV = 0;
		xP = 0;
		yP = 0;
		zP = 0;
	}

	@Override
	protected void onUpdate() {
		double xA = myRobot.accelerometer.getX() * 9.81;// Make sure the
														// accelerometer does
														// measure in g's
		double yA = myRobot.accelerometer.getY() * 9.81;
		double zA = myRobot.accelerometer.getZ() * 9.81;
		xV += xA * .01;
		yV += yA * .01; // Just in case you're wondering, you multiply by .01
						// because that's the time interval in seconds. This
						// ensures that the velocity and later position is in
						// meters.
		zV += zA * .01;
		xP += xV * .01;
		yP += yV * .01;
		zP += zV * .01; // At this point xP, yP, zP are representative of the x,
						// y, and z coordinates of the robot's position with
						// respect to the origin (its starting position at
						// autonomous. It is in meters. You may need to check
						// these units and revise the method in the future.
		boolean isOverObstacle = zP > -.05 && zP < .05 && xP > 3;// Ensure that
																	// forward
																	// is the x
																	// axis and
																	// 3 is a
																	// good
																	// number
																	// for the
																	// distance
																	// from
																	// starting
																	// point to
																	// obstacle.
		switch (autoCase) {// Also ensure that -.05 to .05 is a good range for
							// the z disance.
		case 1: // ROUGH TERRAIN AUTONOMOUS
			myRobot.drivetrain.setSolenoids(0);
			myRobot.drivetrain.setSpeed(0.5, 0.5);
			if (!isOverObstacle) {
				myRobot.drivetrain.setSpeed(0.5, 0.5);
			} else {
				autoCase = 3;
			}
			break;
		case 2: // MOAT AUTONOMOUS
			myRobot.drivetrain.setSolenoids(2);// Change as necessary
			myRobot.drivetrain.setSpeed(1, 1);
			if (!isOverObstacle) {
				myRobot.drivetrain.setSpeed(1, 1);
			} else {
				autoCase = 3;
			}
			break;
		case 3:
			myRobot.drivetrain.setSolenoids(0);
			if (turnDirection == 1) {
				// FIND DISTANCES TO GOAL FROM STARTING POINTS
			}
			if (turnDirection == 2) {

			}
			if (turnDirection == 3) {

			}

		}
	}

	@Override
	protected void onFinish() {
	}

	@Override
	protected boolean shouldFinish() {
		return myRobot.isDisabled() || !myRobot.isAutonomous();
	}

	// Old autonomous code
	/*
	 * public void worstCaseAutonomous() { myRobot.setSafetyEnabled(false);
	 * drivetrain.update(1, 1, false, false); Timer.delay(2.0);//delay duration
	 * subject to change drivetrain.update(0, 0, false, false);//move forward
	 * full speed 2 seconds }
	 * 
	 * public void terrainAutonomous() {//make more terrain autonomous modes
	 * myRobot.setSafetyEnabled(false); gyro.initGyro(); drivetrain.update(1, 1,
	 * true, false); Timer.delay(4.0);//delay duration subject to change if
	 * (gyro.getRate() <= 1 && gyro.getRate() >= -1) {//values subject to change
	 * in future drivetrain.update(1, 1, true, false); Timer.delay(2.0);//delay
	 * duration subject to change } drivetrain.update(0, 0, false, false); }
	 * 
	 * public void terrainAndShootAutonomous() {
	 * myRobot.setSafetyEnabled(false); gyro.initGyro(); drivetrain.update(1, 1,
	 * true, false); Timer.delay(4.0);//delay duration subject to change if
	 * (gyro.getRate() <= 1 && gyro.getRate() >= -1) {//values subject to change
	 * as well drivetrain.update(1, 1, true, false); Timer.delay(2.0);//delay
	 * duration subject to change } drivetrain.update(0, 0, false, false);
	 * angledShooter.update(1, true, false); }
	 */
}
