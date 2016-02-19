package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team2374.robot.Controller.ControllerType;
import org.usfirst.frc.team2374.robot.components.*;
import org.usfirst.frc.team2374.robot.events.Input;
import org.usfirst.frc.team2374.robot.sensors.PositionTracker;

public class Robot extends SampleRobot {

    /**
     * This is the instance of Robot, which is sometimes needed.
     */
    public static Robot robot;
    


    /*
       All of the following variables are components.
     */
    public static Drivetrain drivetrain;
    public static Intake intake;
    public static Manipulator manipulator;
    public static Pistons pistons;
    public static Shooter shooter;

    /*
       All of the following variables are sensors.
     */
    public static PositionTracker positionTracker;

    /**
     * Creates the robot and initializes all of its variables and components.
     */
    public Robot() {
        robot = this;


        //Create all the robot's components
        drivetrain = new Drivetrain(0, 1, 2, 3, 2, 3, 0, 1);
        intake = new Intake(6);
        manipulator = new Manipulator(7);
        pistons = new Pistons(6, 7, 0, 1, 4, 5, 2, 3);
        shooter = new Shooter(4, 5, 5, 6);

        //Create all the robot's sensors
        positionTracker = new PositionTracker(0);
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
        //Run all the following code continuously
        while (true) {
            //Update the input
            Input.update();
            //Switch between different controllers if needed
            ControllerType currentType = ControllerType.getType();
            if (currentType != oldType) {
                COMMANDS.clear();
                currentType.create().start();
                oldType = currentType;
            }
            //Update the time
            deltaTime = Timer.getFPGATimestamp() - prevTime;
            prevTime = Timer.getFPGATimestamp();
            //Update all the robot systems
            for (RobotSystem robotSystem : ROBOT_SYSTEMS) {
                robotSystem.update();
            }
            //Update all the commands
            for (Command command : new ArrayList<>(COMMANDS)) {
                command.update();
                if (command.isFinished()) {
                    command.finish();
                }
            }
            //Delay a small amount of time
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
