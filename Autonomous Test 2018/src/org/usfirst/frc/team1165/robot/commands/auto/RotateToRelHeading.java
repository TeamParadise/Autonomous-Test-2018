package org.usfirst.frc.team1165.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1165.robot.Robot;

/**
 *
 */
public class RotateToRelHeading extends Command
{
	private double deltaSetpoint;
	
	public RotateToRelHeading(double deltaSetpoint)
	{
		this.deltaSetpoint = deltaSetpoint;
		
		requires(Robot.driveTrain);
		requires(Robot.navx);
		requires(Robot.navxController);
	}

	@Override
	protected void initialize()
	{
		double initialAngle = Robot.navx.getAngle();
//		double initialAngle = Robot.navx.getFusedHeading();
		SmartDashboard.putNumber("Initial Angle", initialAngle);
//		
		Robot.navxController.resetInputRange(initialAngle);
		
//		Robot.navxController.setSetpoint(initialAngle + deltaSetpoint); // set setpoint relative does the same thing
		Robot.navxController.setSetpointRelative(deltaSetpoint);
		Robot.navxController.enable();
	}

	@Override
	protected void execute()
	{
//		if(Robot.navxController.onTarget())
//		{
//			Robot.driveTrain.drive(0, 0, 0, 0);
//			SmartDashboard.putNumber("On Target Angle", Robot.navx.getAngle());
//		}
	}

	@Override
	protected boolean isFinished() {
		return Robot.navxController.onTarget();
	}

	@Override
	protected void end()
	{
		double finalAngle = Robot.navx.getAngle();
//		double finalAngle = Robot.navx.getFusedHeading();
		SmartDashboard.putNumber("Final Angle", finalAngle);
		
		Robot.navxController.disable();
	}

	@Override
	protected void interrupted()
	{
		Robot.navxController.disable();
	}
}
