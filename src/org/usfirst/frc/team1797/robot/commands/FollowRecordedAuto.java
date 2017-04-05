package org.usfirst.frc.team1797.robot.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.usfirst.frc.team1797.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowRecordedAuto extends Command {

	public final static FollowRecordedAuto instance = new FollowRecordedAuto();
	
	private BufferedReader bf = null;
	
    public FollowRecordedAuto() {
        requires(Robot.InstanceMap.driveTrain);
    }
    
    public void load(String path) {
    	File rf = new File(path);
    	try {
    		bf = new BufferedReader(new FileReader(rf));
    	} catch (Exception e) {
    		bf = null;
    	}
    }
    private boolean finished = false;

    // Called just before this Command runs the first time
    protected void initialize() {
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
    	String line = bf.readLine();
    	if (line == null) { finished = true; return; }
    	
    	String[] raw = line.split(",");
    	double[] parsed = new double[raw.length];
    	for (int i = 0; i < parsed.length; ++i) parsed[i] = Double.valueOf(raw[i]);
    	Robot.instance.sr.putData(parsed);
    	} catch (IOException e) { e.printStackTrace();
    	} catch (Exception e) { e.printStackTrace();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (bf != null)
			try {
				bf.close();
			} catch (IOException e) { e.printStackTrace();
			}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if (bf != null)
			try {
				bf.close();
			} catch (IOException e) { e.printStackTrace();
			}
    }
}
