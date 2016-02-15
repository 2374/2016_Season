package org.usfirst.frc.team2374.new_code.controllers;

import org.usfirst.frc.team2374.new_code.Controller;
import org.usfirst.frc.team2374.new_code.commands.ForwardsCommand;

public class AutonomousController extends Controller {

    @Override
    public void start() {
        new ForwardsCommand(2).start();
    }
}
