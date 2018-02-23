package org.usfirst.frc.team1165.robot.commands.auto;

import static org.usfirst.frc.team1165.robot.Robot.mDriveTrain;
import static org.usfirst.frc.team1165.robot.Robot.mDriveStraightPID;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command
{
	private double mTarget;

	private double mInitial;
	private double mFinal;
	
	public DriveStraight(double target)
	{
		mTarget = target;
		
		requires(mDriveTrain);
		requires(mDriveStraightPID);
	}

	@Override
	protected void initialize()
	{
		mInitial = mDriveTrain.getPosition();
		
		DriverStation.reportWarning("Initial Position: " + mInitial, false);

		mDriveStraightPID.resetInputRange(mTarget * 2);
		mDriveStraightPID.setSetpointRelative(mTarget);
		mDriveStraightPID.enable();
	}

	@Override
	protected boolean isFinished()
	{
		return mDriveStraightPID.onTarget();
	}

	@Override
	protected void end()
	{
		mFinal = mDriveTrain.getPosition();
		
		DriverStation.reportWarning("Final Position: " + mFinal, false);
		DriverStation.reportWarning("Delta Position: " + (mFinal - mInitial), false);

		mDriveTrain.stop();
		mDriveStraightPID.disable();
	}
}