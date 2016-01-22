package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.Joystick;

public class TeleopController extends RobotController {

	private Joystick controller;

	public TeleopController(Robot robot) {
		super(robot);
	}

	@Override
	protected void onStart() {
		controller = new Joystick(0);

		delay(5, () -> {
			//do something
		});
	}

	@Override
	protected void onUpdate() {
		myRobot.angledShooter.update(2, controller.getRawButton(5),
				controller.getRawButton(6));
		boolean backSolPressed = controller.getRawAxis(2) != 0;
		boolean frontSolPressed = controller.getRawAxis(3) != 0;
		myRobot.drivetrain.update(controller.getRawAxis(1),
				controller.getRawAxis(5), frontSolPressed, backSolPressed);
		myRobot.intake.update(controller.getRawButton(1),
				controller.getRawButton(3));
		myRobot.chainlift.update(controller.getRawButton(2),
				controller.getRawButton(4));
	}

	@Override
	protected void onFinish() {
		//do nothing
	}

	@Override
	protected boolean shouldFinish() {
		return myRobot.isDisabled() || !myRobot.isOperatorControl();
	}
}
