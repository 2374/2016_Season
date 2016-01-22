package org.usfirst.frc.team2374.robot;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.wpi.first.wpilibj.Timer;
 
public abstract class RobotController {
	
	private Map<Runnable, Double> toRun = new HashMap<>();
	protected Robot myRobot;
	
	public RobotController(Robot robot) {
		myRobot = robot;
	}
	
	protected void delay(double time, Runnable r) {
		toRun.put(r, Timer.getFPGATimestamp() + time);
	}
	
	void run() {
		onStart();
		while (!shouldFinish()) {
			for (Entry<Runnable, Double> e : toRun.entrySet()) {
				if (Timer.getFPGATimestamp() > e.getValue()) {
					e.getKey().run();
					toRun.remove(e.getKey());
				}
			}
			onUpdate();
			Timer.delay(.01);
		}
		onFinish();
	}

	protected abstract void onStart();
	protected abstract void onUpdate();
	protected abstract void onFinish();
	protected abstract boolean shouldFinish();
}
