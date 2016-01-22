package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.Timer;
 

public class AutonomousController extends RobotController{

	public AutonomousController(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
        myRobot.drivetrain.update(1, 1, false, false);
        delay(2,() -> {
			//do something
            myRobot.drivetrain.update(0, 0, false, false);//move forward full speed 2 seconds
		});//delay duration subject to change
	}

	@Override
	protected void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onFinish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean shouldFinish() {
		// TODO Auto-generated method stub
		return myRobot.isDisabled() || !myRobot.isAutonomous();	}

}
