package org.usfirst.frc.team1165.robot.commands.auto;

import static org.usfirst.frc.team1165.robot.Robot.mDriveTrain;
import static org.usfirst.frc.team1165.robot.Robot.mRotatePID;
import static org.usfirst.frc.team1165.util.drivers.NavX.getFusedHeading;

import static edu.wpi.first.wpilibj.DriverStation.reportWarning;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToRelHeading extends Command
{
	private double mTarget;

	private double mInitial;
	private double mFinal;
	
	public RotateToRelHeading(double target)
	{
		mTarget = target;

		requires(mDriveTrain);
		requires(mRotatePID);
	}

	@Override
	protected void initialize()
	{
		mInitial = getFusedHeading();
		
		reportWarning("Initial Angle: " + mInitial, false);

		mRotatePID.setSetpointRelative(mTarget);
		mRotatePID.enable();
	}

	@Override
	protected boolean isFinished()
	{
		return mRotatePID.onTarget();
	}

	@Override
	protected void end()
	{
		mFinal = mDriveTrain.getPosition();
		
		reportWarning("Final Angle: " + mFinal, false);
		reportWarning("Delta Angle: " + (mFinal - mInitial), false);

		mRotatePID.disable();
	}
}