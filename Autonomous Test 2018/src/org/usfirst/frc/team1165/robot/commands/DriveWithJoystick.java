package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1165.robot.Robot;

/**
 *
 */
public class DriveWithJoystick extends Command
{
	public DriveWithJoystick()
	{
		requires(Robot.driveTrain);
		requires(Robot.navx);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		Robot.driveTrain.drive(Robot.oi.getX(), Robot.oi.getY(), Robot.oi.getTwist(), 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
}
