package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDComponent extends PIDSubsystem{

	public PIDComponent(double p, double i, double d) {
		super(p, i, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
