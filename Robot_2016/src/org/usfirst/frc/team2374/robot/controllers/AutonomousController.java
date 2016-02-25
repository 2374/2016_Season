package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Controller;
import org.usfirst.frc.team2374.robot.commands.ForwardsCommand;
import org.usfirst.frc.team2374.robot.commands.IntakeAfterShooter;
import org.usfirst.frc.team2374.robot.commands.MoveToSomewhere;
import org.usfirst.frc.team2374.robot.commands.ShooterAutonomous;

public class AutonomousController extends Controller {

    @Override
    public void start() {
        new ForwardsCommand(2).start();

        //we need to initialize the accelermometer and gyro
        //Go forwards
        //Move to somewhere
        //Shoot
        //We need to implement autocases, solenoid command, and over obstacle command
        Command forwards = new ForwardsCommand(2);
        Command moveToSomewhere = new MoveToSomewhere(0, 0);
        Command intake = new IntakeAfterShooter();
        Command shoot = new ShooterAutonomous();

        //forwards.thenRun(moveToSomewhere).thenRun(shoot);
        moveToSomewhere.thenRun(forwards).thenRun(shoot).thenRun(intake);
        moveToSomewhere.start();
    }
}
