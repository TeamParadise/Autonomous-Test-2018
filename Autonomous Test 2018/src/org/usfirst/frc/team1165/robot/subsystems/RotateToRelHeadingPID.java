package org.usfirst.frc.team1165.robot.subsystems;

import static org.usfirst.frc.team1165.robot.Robot.mDriveTrain;
import static org.usfirst.frc.team1165.util.drivers.NavX.getFusedHeading;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToRelHeadingPID extends PIDSubsystem
{
	private double mTwist;

	public RotateToRelHeadingPID()
	{
		super("NavX PID", 0.1, 0, 0, 0);

		setInputRange(0, 360);
		setOutputRange(-.75, .75);
		setAbsoluteTolerance(10);

		getPIDController().setContinuous();
	}

	@Override
	public void initDefaultCommand()
	{
	}

	@Override
	protected double returnPIDInput()
	{
		return getFusedHeading();
	}

	@Override
	protected void usePIDOutput(double twist)
	{
		mTwist = twist;
		mDriveTrain.arcadeDriveDamped(0, twist);
	}

	public void report()
	{
		SmartDashboard.putNumber("NavX PID Target", getSetpoint());
		SmartDashboard.putNumber("NavX PID Output", mTwist);
		SmartDashboard.putBoolean("NavX PID On Target", onTarget());
//		 if (onTarget()) DriverStation.reportWarning("On Target: " + getFusedHeading(), false);
	}
}
