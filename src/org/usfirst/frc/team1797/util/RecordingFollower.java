package org.usfirst.frc.team1797.util;

import org.usfirst.frc.team1797.robot.subsystems.DriveTrain;

public class RecordingFollower {
	
	@SuppressWarnings("unused")
	private double kp, ki, kd;
	private double initialPosition_l;
	private double initialPosition_r;
	
	private DriveTrain drivetrain;
	
	public RecordingFollower(DriveTrain drivetrain) {
		this.drivetrain = drivetrain;
	}
	
	public void setPIDVA(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}
	
	public void setStartPoint() {
		this.initialPosition_l = drivetrain.getLeftDistance();
		this.initialPosition_r = drivetrain.getRightDistance();
	}
	
	public void reset() {
		err_last_l = 0;
		err_last_r = 0;
	}
	
	double err_last_l = 0, err_last_r = 0;
	public void drive(double dt, double leftF, double rightF, double leftPos, double rightPos) {
		double distance_l = drivetrain.getLeftDistance() - initialPosition_l;
		double distance_r = drivetrain.getRightDistance() - initialPosition_r;

		double err_l = leftPos - distance_l;
		double err_r = rightPos - distance_r;
		double left_value = kp * err_l
//				+ ki * (err + err_last) * dt
				+ kd * (err_l - err_last_l) / dt
				+ leftF;
		double right_value = kp * err_r
//				+ ki * (err + err_last) * dt
				+ kd * (err_r - err_last_r) / dt
				- rightF;
		err_last_l = err_l;
		err_last_r = err_r;
			
		//debug statement
//		System.out.println(err_l + "\t" + err_r);
		
		drivetrain.setLeftSpeed(left_value);
		drivetrain.setRightSpeed(-right_value);
	}
	
}
