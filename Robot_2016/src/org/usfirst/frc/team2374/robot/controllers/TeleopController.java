package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Controller;
import org.usfirst.frc.team2374.robot.commands.IntakeTeleopCommand;
import org.usfirst.frc.team2374.robot.commands.ManipulatorTeleopCommand;
import org.usfirst.frc.team2374.robot.commands.PistonsTeleopCommand;
import org.usfirst.frc.team2374.robot.commands.TankDriveCommand;

public class TeleopController extends Controller {

    @Override
    public void start() {
        //This controls the robot's wheels
        new TankDriveCommand().startAsDefaultCommand();

        //controls the pistons
        new PistonsTeleopCommand().startAsDefaultCommand();

        //controls the intake
        new IntakeTeleopCommand().startAsDefaultCommand();

        //controls the manipulator
        new ManipulatorTeleopCommand().startAsDefaultCommand();

        //Make the robot go forward for 1 second on the A button
        //Input.whenPressed(1).runCommand(new ForwardsCommand(1));
    }
}
