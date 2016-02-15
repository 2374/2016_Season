package org.usfirst.frc.team2374.new_code.controllers;

import org.usfirst.frc.team2374.new_code.Controller;
import org.usfirst.frc.team2374.new_code.commands.ForwardsCommand;
import org.usfirst.frc.team2374.new_code.commands.TankDriveCommand;
import org.usfirst.frc.team2374.new_code.events.Input;

public class TeleopController extends Controller {

    @Override
    public void start() {
        //This controls the robot's wheels
        new TankDriveCommand().startAsDefaultCommand();

        //Make the robot go forward for 1 second on the A button
        Input.whenPressed(1).runCommand(new ForwardsCommand(1));
    }
}
