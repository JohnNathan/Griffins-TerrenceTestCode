package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.commands.DriveCommand;
import org.usfirst.frc.team1797.util.RecordingFollower;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem implements Recordable {

    private VictorSP left, right;
    private Encoder leftEnc, rightEnc;
    
    public DriveTrain() {
    	left = new VictorSP(0);
    	right = new VictorSP(1);
    	
    	leftEnc = new Encoder(0, 1);
    	leftEnc.setDistancePerPulse(.0481);
    	rightEnc = new Encoder(2, 3, true);
    	rightEnc.setDistancePerPulse(.0481);
    }
    
    public void setLeftSpeed(double speed) {
    	left.set(speed);
    }
    public void setRightSpeed(double speed) {
    	right.set(speed);
    }
    
    public void resetEncoders() {
    	leftEnc.reset();
    	rightEnc.reset();
    }
    
    public double getLeftDistance() {
    	return leftEnc.getDistance();
    }
    public double getRightDistance() {
    	return rightEnc.getDistance();
    }

    public void initDefaultCommand() {
        this.setDefaultCommand(new DriveCommand());
    }
    
    // dt, leftSpeed, rightSpeed, leftDistance, rightDistance
    private long prev_timestamp = 0L;
    @Override
    public double[] getData() {
    	double dt = (-prev_timestamp + (prev_timestamp=System.currentTimeMillis()))/1000.0; //calculate and reassign
    	return new double[] { dt, left.get(), right.get(), this.getLeftDistance(), this.getRightDistance() };
    }
    
    private RecordingFollower rf;
    {
    	rf = new RecordingFollower(this);
    	rf.setPIDVA(0.01, 0.0, 0.001);
    }
    public void startFollowRecording() {
    	this.rf.setStartPoint();
    	this.rf.reset();
    }
    // dt, leftSpeed, rightSpeed, leftPosition, rightPosition
    @Override
    public void putData(double[] data) {
    	if (data.length != 5) return;
    	rf.drive(data[0], data[1], data[2], data[3], data[4]);
    }
}

