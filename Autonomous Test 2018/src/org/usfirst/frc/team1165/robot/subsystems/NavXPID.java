package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NavXPID extends PIDSubsystem
{

	private static final double kP = 0.10;
	private static final double kI = 0.00;
	private static final double kD = 0.00;
	private static final double kF = 0.00;
	
	private static final double TOLERANCE = 10;
	
	private double output;
	
    public NavXPID()
    {
    	super("NavX PID", kP, kI, kD, kF);

//    	setInputRange(0, 360); // using fused heading
    	
    	resetInputRange(0); // using angle
    	setOutputRange(-.3, .3);
    	setAbsoluteTolerance(TOLERANCE);

//    	getPIDController().setContinuous(); // using fused heading
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void resetInputRange(double angle) {
    	setInputRange(angle - 180, angle + 180); // using angle
    }

    protected double returnPIDInput() {
//    	return Robot.navx.getFusedHeading();  // using fused heading
    	return Robot.navx.getAngle(); // using angle
    }

    protected void usePIDOutput(double output) {
    	this.output = output;
    	Robot.driveTrain.drive(0, 0, output, 0);
    }
    
    public void report()
    {
    	SmartDashboard.putNumber("NavX PID Target", getSetpoint());
    	SmartDashboard.putNumber("NavX PID Output", output);
    	SmartDashboard.putBoolean("NavX PID On Target", onTarget());
//    	if (onTarget()) DriverStation.reportError("On Target", false);
    }
}
