package org.usfirst.frc.team2374.new_code.controllers;

import org.usfirst.frc.team2374.new_code.Controller;
import org.usfirst.frc.team2374.new_code.commands.TankDriveCommand;

public class TeleopController extends Controller {

    @Override
    public void start() {
        //This controls the robot's wheels
        new TankDriveCommand().startAsDefaultCommand();
    }
}
