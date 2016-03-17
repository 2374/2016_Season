package org.usfirst.frc.team2374.robot.components;

import org.usfirst.frc.team2374.robot.Component;

import edu.wpi.first.wpilibj.Relay;

public class SidePiston extends Component {

	private final Relay spike;

	public SidePiston(int port) {
		spike = new Relay(port);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
