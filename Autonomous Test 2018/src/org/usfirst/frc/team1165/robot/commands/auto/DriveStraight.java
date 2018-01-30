package org.usfirst.frc.team1165.robot.commands.auto;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command
{
	private double targetDistance;
	
    public DriveStraight(double targetDistance)
    {
    	this.targetDistance = targetDistance;
		
		requires(Robot.driveTrain);
	}

    // Called just before this Command runs the first time
    protected void initialize()
    {
    	double initialDistance = Robot.driveTrain.getAverageEncoderDistance();

		Robot.driveTrainPID.resetInputRange(initialDistance, Math.max(targetDistance * 2, 50));
		Robot.driveTrainPID.setSetpoint(initialDistance + targetDistance);
		Robot.driveTrainPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrainPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	Robot.driveTrain.drive(0, 0, 0, 0);
		Robot.driveTrainPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}