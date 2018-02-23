package org.usfirst.frc.team1165.robot.subsystems;

import static org.usfirst.frc.team1165.robot.Robot.mDriveTrain;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightPID extends PIDSubsystem
{
	private double mOutput;

	public DriveStraightPID()
	{
		super("Drive Straight PID", 0.1, 0, 0.05, 0.01);
//		super("Drive Straight PID", 0.1, 0, 0.01, 0.1);

		resetInputRange(100);
		setInputRange(0, 100);
		setOutputRange(-.5, .5);
		setAbsoluteTolerance(1);

		getPIDController().setContinuous(false);
	}

	public void resetInputRange(double overUnder) {
		setInputRange(mDriveTrain.getPosition() - overUnder, mDriveTrain.getPosition() + overUnder);
	}
	
	@Override
	public void initDefaultCommand()
	{
	}

	@Override
	protected double returnPIDInput()
	{
		return mDriveTrain.getPosition();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		mOutput = output;
		mDriveTrain.arcadeDriveDamped(output, 0);
	}

	public void report()
	{
		SmartDashboard.putNumber("Drive Straight PID Target", getSetpoint());
		SmartDashboard.putNumber("Drive Straight PID Output", mOutput);
		SmartDashboard.putBoolean("Drive Straight PID On Target", onTarget());
//		 if (onTarget()) DriverStation.reportWarning("On Target: " + getFusedHeading(), false);
	}
}
