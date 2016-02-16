package org.usfirst.frc.team2374.new_code;

import org.usfirst.frc.team2374.new_code.Robot.RobotSystem;

public abstract class Component extends RobotSystem {

    /**
     * Which command is current controlling this component.
     */
    Command command;

    /**
     * The command that controls this component when no other command needs the
     * component.
     */
    Command defaultCommand;
}
