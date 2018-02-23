package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team1165.robot.Robot.mDriveTrain;

/**
 *
 */
public class ResetEncoder extends Command
{
	public ResetEncoder()
	{
		requires(mDriveTrain);
	}

	@Override
	protected void initialize()
	{
		mDriveTrain.resetEncoder();
	}
	
	@Override
	protected boolean isFinished()
	{
		return mDriveTrain.isZeroed();
	}
}
