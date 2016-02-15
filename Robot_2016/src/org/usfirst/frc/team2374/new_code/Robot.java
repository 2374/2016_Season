package org.usfirst.frc.team2374.new_code;

import edu.wpi.first.wpilibj.SampleRobot;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.usfirst.frc.team2374.new_code.Controller.ControllerType;
import org.usfirst.frc.team2374.new_code.components.*;
import org.usfirst.frc.team2374.new_code.events.Input;

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

    /**
     * The list of all current running commands.
     */
    public static final List<Command> COMMANDS = new LinkedList<>();

    /**
     * Creates the robot and initializes all of its variables and components.
     */
    public Robot() {
        robot = this;

        //Create all the robot's components
        drivetrain = new Drivetrain(0, 1, 2, 3);
        intake = new Intake(6);
        manipulator = new Manipulator(7);
        pistons = new Pistons(6, 7, 0, 1, 4, 5, 2, 3);
        shooter = new Shooter(4, 5);
    }

    /**
     * This function is called when the robot starts. It contains all of the
     * code for switching between different controllers.
     */
    @Override
    public void robotMain() {
        ControllerType oldType = null;
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
            //Update all the commands
            new ArrayList<>(COMMANDS).forEach(a -> {
                a.update();
                if (a.isFinished()) {
                    a.finish();
                }
            });
        }
    }
}
