package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.sensors.PositionTracker;

public class MoveToSomewhere {
	
	
	public static Command moveTo(double goal_x, double goal_y) {
		double xP=Robot.positionTracker.position.x;
		double yP=Robot.positionTracker.position.y;
		
		Command turnToCorrectY = turn(yP < goal_y ? 90 : -90);
		Command moveToCorrectY = new ForwardsPositionCommand(goal_y, false);
		Command turnToCorrectX = turn(0);
		Command moveToCorrectX = new ForwardsPositionCommand(goal_x, true);
		
		turnToCorrectY.thenRun(moveToCorrectY).thenRun(turnToCorrectX).thenRun(moveToCorrectX);
		
		turnToCorrectY.start();
		
		return moveToCorrectX;
	}
	
	
	/*double goal_x, goal_y;
	double xP,yP;*/
	static Command turn(double turnAngle){return new TurnCommand(turnAngle);}
	/*static Command forward(double time){return new ForwardsPositionCommand();}
	public MoveToSomewhere(double goal_x, double goal_y){
		this.goal_x=goal_x;
		this.goal_y=goal_y;
		xP=Robot.positionTracker.position.x;
		yP=Robot.positionTracker.position.y;
	}
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return xP==goal_x&&yP==goal_y;
	}

	@Override
	public List<Component> requires() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		xP=Robot.positionTracker.position.x;
		yP=Robot.positionTracker.position.y;
		
		if (yP<goal_y&&Robot.positionTracker.direction() != 90){
			turn(90);
		}
		else if (yP > goal_y && Robot.positionTracker.direction() != -90){
			turn(-90);
		}
		else {forward(.1);}
		if(yP==goal_y&&xP<goal_x&&Robot.positionTracker.direction() != 0)
		{
			turn(0);
			}
		else{forward());}
		}*/

}
