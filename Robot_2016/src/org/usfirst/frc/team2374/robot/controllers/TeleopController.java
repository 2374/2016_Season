package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Controller;
import org.usfirst.frc.team2374.robot.commands.ForwardsCommand;
import org.usfirst.frc.team2374.robot.commands.PistonsCommand;
import org.usfirst.frc.team2374.robot.commands.TankDriveCommand;
import org.usfirst.frc.team2374.robot.events.Input;

public class TeleopController extends Controller {

    @Override
    public void start() {
        //This controls the robot's wheels
        new TankDriveCommand().startAsDefaultCommand();
        
        //controls the pistons
        new PistonsCommand().startAsDefaultCommand();

        //Make the robot go forward for 1 second on the A button
        Input.whenPressed(1).runCommand(new ForwardsCommand(1));
    }
}
