package org.usfirst.frc.team2374.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Robot;

public abstract class TimedCommand extends Command {

    /**
     * What time the command should end at.
     */
    private double endTime;

    /**
     * Creates a new command that ends after a given amount of time.
     *
     * @param time The amount of time after which the command should end (in
     * seconds).
     */
    public TimedCommand(double time) {
        onStart.add(() -> endTime = time + Timer.getFPGATimestamp());
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() > endTime|| !Robot.robot.isAutonomous();
    }
}
