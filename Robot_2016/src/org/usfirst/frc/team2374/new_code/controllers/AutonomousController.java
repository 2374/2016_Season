package org.usfirst.frc.team2374.new_code.controllers;

import org.usfirst.frc.team2374.new_code.Command;
import org.usfirst.frc.team2374.new_code.Controller;
import org.usfirst.frc.team2374.new_code.commands.ForwardsCommand;

public class AutonomousController extends Controller {

    @Override
    public void start() {
        new ForwardsCommand(2).start();

        //Go forwards
        //Move to somewhere
        //Shoot
        Command forwards = new ForwardsCommand(2);
        Command moveToSomewhere = null;
        Command shoot = null;

        //forwards.thenRun(moveToSomewhere).thenRun(shoot);
        moveToSomewhere.thenRun(forwards).thenRun(shoot);
        moveToSomewhere.start();
    }
}
