package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainPID extends Subsystem
{
	private PIDController leftController;
	private PIDController rightController;

	private double leftOutput;
	private double rightOutput;

	public DriveTrainPID()
	{
		leftController = new PIDController(0.01, 0, 0, 0.01, new PIDSource()
		{
			public void setPIDSourceType(PIDSourceType pidSource)
			{
			}

			public PIDSourceType getPIDSourceType()
			{
				return PIDSourceType.kDisplacement;
			}

			public double pidGet()
			{
				return Robot.driveTrain.getLeftDistanceInches();
			}
		}, new PIDOutput()
		{
			public void pidWrite(double output)
			{
				leftOutput = output;
				Robot.driveTrain.tankDriveLeft(output);
			}
		});

		rightController = new PIDController(0.01, 0, 0, 0.01, new PIDSource()
		{
			public void setPIDSourceType(PIDSourceType pidSource)
			{
			}

			public PIDSourceType getPIDSourceType()
			{
				return PIDSourceType.kDisplacement;
			}

			public double pidGet()
			{
				return Robot.driveTrain.getRightDistanceInches();
			}
		}, new PIDOutput()
		{
			public void pidWrite(double output)
			{
				rightOutput = output;
				Robot.driveTrain.tankDriveRight(output);
			}
		});

		resetInputRange(100);

		leftController.setOutputRange(-0.3, 0.3);
		leftController.setAbsoluteTolerance(5);
		leftController.setContinuous(false);

		rightController.setOutputRange(-0.3, 0.3);
		rightController.setAbsoluteTolerance(5);
		rightController.setContinuous(false);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void resetInputRange(double overUnder)
	{
		leftController.setInputRange(-overUnder, overUnder);
		rightController.setInputRange(-overUnder, overUnder);
	}

	public void enable()
	{
		leftController.enable();
		rightController.enable();
	}

	public void disable()
	{
		leftController.disable();
		rightController.disable();
	}

	public void setSetpoint(double targetDistance)
	{
		leftController.setSetpoint(targetDistance);
		rightController.setSetpoint(targetDistance);
	}

	public boolean onTarget()
	{
		return leftController.onTarget() && rightController.onTarget();
	}

	public void report()
	{
		SmartDashboard.putNumber("Drive Train PID Left Output", leftOutput);
		SmartDashboard.putNumber("Drive Train PID Right Output", rightOutput);
		SmartDashboard.putBoolean("Drive Train PID On Target", onTarget());
	}
}
