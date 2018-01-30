package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DriveTrain_PID extends PIDSubsystem {

	private static final double kP = 0.01;
	private static final double kI = 0.00;
	private static final double kD = 0.00;
	private static final double kF = 0.00;
	
	private static final double TOLERANCE = 10;
	
    public DriveTrain_PID()
    {
    	super("Drive Train", kP, kI, kD, kF);
    	
    	setInputRange(-100, 100);
    	setOutputRange(-0.4, 0.4);
    	setAbsoluteTolerance(TOLERANCE);
    	getPIDController().setContinuous(false);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void resetInputRange(double encoderValue, double overUnder) {
    	setInputRange(encoderValue - overUnder, encoderValue + overUnder);
    }
    
    protected double returnPIDInput() {
        return Robot.driveTrain.getAverageEncoderDistance();
    }

    protected void usePIDOutput(double output) {
    	Robot.driveTrain.drive(0, output, 0, 0);
    }
    
    public void report()
    {
    	//
    }
}
