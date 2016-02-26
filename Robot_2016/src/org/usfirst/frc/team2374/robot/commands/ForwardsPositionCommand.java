	package org.usfirst.frc.team2374.robot.commands;

	import java.util.Arrays;
	import java.util.List;

	import org.usfirst.frc.team2374.robot.Command;
	import org.usfirst.frc.team2374.robot.Component;
	import org.usfirst.frc.team2374.robot.Robot;
	import org.usfirst.frc.team2374.robot.math.MathUtil;

	public class ForwardsPositionCommand extends Command{
		
		public double target;
		public boolean isX;
		
		public ForwardsPositionCommand(double t, boolean x){
			target = t;
			isX = x;
		}

		@Override
		public boolean isFinished() {
			double position = Robot.positionTracker.position.y;
			if (isX)
				position = Robot.positionTracker.position.x;
			return MathUtil.valueNear(position, target, 0.5);
		}

		@Override
		public List<Component> requires() {
			return Arrays.asList(Robot.drivetrain);
		}

		@Override
		public void update() {
			Robot.drivetrain.setSpeed(1,1);
			
		}

	}
