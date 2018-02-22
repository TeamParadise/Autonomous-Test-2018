package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainPID extends Subsystem
{
	private PIDController rightController = new PIDController(0.01, 0, 0, 0.01, Robot.driveTrain, Robot.driveTrain);

	private double initHeading;

	public enum Correction
	{
		kLeft, kRight, kNone
	}

	public DriveTrainPID()
	{
		resetInputRange(100);

		rightController.setOutputRange(-0.3, 0.3);
		rightController.setAbsoluteTolerance(2);
		rightController.setContinuous(false);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void resetInputRange(double overUnder)
	{
		rightController.setInputRange(-overUnder, overUnder);
	}

	public void enable()
	{
		rightController.enable();

		initHeading = Robot.navx.getFusedHeading();
	}

	public void disable()
	{
		rightController.disable();
	}

	public void setSetpoint(double targetDistance)
	{
		rightController.setSetpoint(targetDistance);
	}

	public boolean onTarget()
	{
		return rightController.onTarget();
	}

	public Correction getTwistCorrection()
	{
		// if the difference in heading is greater than absolute tolerance...
		if (Math.abs(Robot.navx.getFusedHeading() - initHeading) > 2)
			// ...and the robot is to the left of heading.
			if (Robot.navx.getFusedHeading() > initHeading)
				return Correction.kLeft;
			// ...and the robot is to the right.
			else
				return Correction.kRight;
		// if the difference in heading is within tolerance..
		else
			// ...do nothing.
			return Correction.kNone;
	}

	public void report()
	{
		SmartDashboard.putBoolean("Drive Train PID On Target", onTarget());
		SmartDashboard.putNumber("Drive Train PID Initial Heading", initHeading);
	}
}
