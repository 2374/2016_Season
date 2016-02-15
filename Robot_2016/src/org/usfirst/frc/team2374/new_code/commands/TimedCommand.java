package org.usfirst.frc.team2374.new_code.commands;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team2374.new_code.Command;

public abstract class TimedCommand extends Command {

    /**
     * What time the command should end at.
     */
    private final double endTime;

    /**
     * Creates a new command that ends after a given amount of time.
     *
     * @param time The amount of time after which the command should end (in
     * seconds).
     */
    public TimedCommand(double time) {
        endTime = time + Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() > endTime;
    }
}
