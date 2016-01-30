package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class CrusaderDashboard extends SmartDashboard{
	private final SmartDashboard dashboard=new SmartDashboard();
	private Robot myRobot=new Robot();
	
	public CrusaderDashboard() {
		dashboard.putNumber("Encoder Speed", myRobot.drivetrain.getRate());
	}
}
