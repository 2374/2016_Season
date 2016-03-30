package org.usfirst.frc.team2374.robot.components;

import org.usfirst.frc.team2374.robot.Component;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class Manipulator extends Component {

	/**
	 * The robot's manipulator motor.
	 */
	private final Talon motor;
	public final DigitalInput LimitSwitchFront;

	// public final DigitalInput LimitSwitchBack;

	/**
	 * Creates a manipulator with motor set to the given port.
	 *
	 * @param port
	 *            The port for the motor.
	 */
	public Manipulator(int port, int port1) {
		motor = new Talon(port);
		LimitSwitchFront = new DigitalInput(port1);
		// LimitSwitchBack = new DigitalInput(port2);
	}

	/**
	 * This function sets the manipulator to run at a given speed.
	 *
	 * @param speed
	 *            The speed of the motor.
	 */
	public void setSpeed(double speed) {
		motor.set(speed);
	}

	@Override
	public void update() {
		setSpeed(0);
	}
}
