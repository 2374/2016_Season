package org.usfirst.frc.team2374.robot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team2374.robot.Controller.ControllerType;
import org.usfirst.frc.team2374.robot.components.Drivetrain;
import org.usfirst.frc.team2374.robot.components.Intake;
import org.usfirst.frc.team2374.robot.components.Manipulator;
import org.usfirst.frc.team2374.robot.components.MountedCamera;
import org.usfirst.frc.team2374.robot.components.Pistons;
import org.usfirst.frc.team2374.robot.components.Shooter;
import org.usfirst.frc.team2374.robot.events.Input;
import org.usfirst.frc.team2374.robot.sensors.PositionTracker;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {

	/**
	 * This is the instance of Robot, which is sometimes needed.
	 */
	public static Robot robot;

	/*
	 * All of the following variables are components.
	 */
	public static Drivetrain drivetrain;
	public static Intake intake;
	public static Manipulator manipulator;
	public static Pistons pistons;
	public static Shooter shooter;
	public static MountedCamera camera;
	public static SendableChooser autoChooserObstacles;
	public static SendableChooser autoChooserPositions;

	/*
	 * All of the following variables are sensors.
	 */
	public static PositionTracker positionTracker;

	/**
	 * Creates the robot and initializes all of its variables and components.
	 */
	public Robot() {
		robot = this;

		// Create all the robot's components
		drivetrain = new Drivetrain(0, 1, 2, 3, 2, 3, 0, 1);
		intake = new Intake(6);
		manipulator = new Manipulator(7);
		pistons = new Pistons(6, 7, 0, 1, 4, 5, 2, 3);
		shooter = new Shooter(4, 5, 4, 5);
		camera = new MountedCamera("10.23.74.142"); // Roborio IP Address
		// Create all the robot's sensors
		positionTracker = new PositionTracker(0);
		autoChooserObstacles = new SendableChooser();
		autoChooserPositions = new SendableChooser();
		
		autoChooserObstacles.addDefault("Rough Terrain", 0);//these numbers represent piston configs, change as necessary
												  //rough terrain: all off
		autoChooserObstacles.addObject("Moat", 3);// moat: front on
		autoChooserObstacles.addObject("Rock Wall", 2);//rock wall: all on
		autoChooserObstacles.addObject("Fixed Ramp", 12);//fixed ramp: front left off
		autoChooserPositions.addObject("Position 2, Line the robot up with the left edge of the obstacle", 5);//switch these numbers around in Robot and auto controller in case it conflicts with piston configs
		autoChooserPositions.addObject("Position 3, Line the robot up with the left edge of the obstacle", 6);
		autoChooserPositions.addObject("Position 4, Line the robot up with the left edge of the obstacle", 7);
		autoChooserPositions.addObject("Position 5, Line the robot up with the left edge of the obstacle", 8);
		
		//if (Robot.robot.isAutonomous()){
			SmartDashboard.putData("Auto Obstacle Chooser", autoChooserObstacles); 
			SmartDashboard.putData("Auto Position Chooser", autoChooserPositions);
			//}
		
	}

	/**
	 * The list of all current running commands.
	 */
	static final List<Command> COMMANDS = new LinkedList<>();

	/**
	 * The time (in seconds) since the last update.
	 */
	public static double deltaTime;

	/**
	 * This function is called when the robot starts. It contains all of the
	 * code for switching between different controllers.
	 */
	@Override
	public void robotMain() {
		ControllerType oldType = null;
		double prevTime = Timer.getFPGATimestamp();
		// Run all the following code continuously
		while (true) {
			// Update the input
			Input.update();
			// Switch between different controllers if needed
			ControllerType currentType = ControllerType.getType();
			if (currentType != oldType) {
				System.out.println(currentType.name());
				COMMANDS.clear();
				currentType.create().start();
				oldType = currentType;
			}
			// Update the time
			deltaTime = Timer.getFPGATimestamp() - prevTime;
			prevTime = Timer.getFPGATimestamp();
			// Update all the robot systems
			for (RobotSystem robotSystem : ROBOT_SYSTEMS) {
				robotSystem.update();
			}
			// Update all the commands
			SmartDashboard.putString("Command List", COMMANDS.toString());
			for (Command command : new ArrayList<>(COMMANDS)) {
				command.update();
				if (command.isFinished()) {
					command.finish();
				}
			}
			// Delay a small amount of time
			Timer.delay(.01);
		}
	}

	/**
	 * The list of all of the robot systems.
	 */
	private static final List<RobotSystem> ROBOT_SYSTEMS = new LinkedList<>();

	/**
	 * Robot systems represent parts of the robot that act continuously and are
	 * not enabled or disabled. Most robot systems are components or sensors.
	 */
	public static abstract class RobotSystem {

		/**
		 * This creates a robot system and adds it to the list of all robot
		 * systems.
		 */
		public RobotSystem() {
			ROBOT_SYSTEMS.add(this);
		}

		/**
		 * This function is called continuously.
		 */
		public abstract void update();
	}
}