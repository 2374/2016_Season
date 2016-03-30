package org.usfirst.frc.team2374.robot.components;

import org.usfirst.frc.team2374.robot.Component;

import edu.wpi.first.wpilibj.Relay;

public class SidePiston extends Component {

	public final Relay spike;

	public SidePiston(int port) {
		spike = new Relay(port);
	}

	/**
	 * Sets the spike to move forwards, reverse, or to deactivate
	 * 
	 * @param val
	 *            An integer value representing the motion of the spike: 1
	 *            represents forward, 0 represents off, and -1 represents
	 *            reverse.
	 */
	public void setValue(int val) {
		if (val == 1) {
			spike.set(Relay.Value.kForward);
		}
		if (val == 0) {
			spike.set(Relay.Value.kOff);
		}
		if (val == -1) {
			spike.set(Relay.Value.kReverse);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
