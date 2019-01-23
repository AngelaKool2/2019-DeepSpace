package org.usfirst.frc.team7327.robot.subsystems;

import org.usfirst.frc.team7327.robot.Robot;
import org.usfirst.frc.team7327.robot.SwerveModule;
import org.usfirst.frc.team7327.robot.TurnModule;
import org.usfirst.frc.team7327.robot.commands.SwerveDrive;



import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class DriveTrain extends Subsystem {
	
	private SwerveModule moduleNE, moduleNW, moduleSE, moduleSW;
	
	public TurnModule turning; 
	
	static final double kP = 2.5;
	static final double kI = 0;
	static final double kD = 0;
	
	static final double tkP = .4;  //.4 cement , .6 carpet
	static final double tkI = .000001;
	static final double tkD = .04; //.04 cement , .05 carpet
	
	
	public static Potentiometer abeNW = new AnalogPotentiometer(0, 360, -165.7); 
	public static Potentiometer abeNE = new AnalogPotentiometer(1, 360, -63.85);
	public static Potentiometer abeSW = new AnalogPotentiometer(2, 360, -11.3); 
	public static Potentiometer abeSE = new AnalogPotentiometer(3, 360, -279.7); 
	
	public DriveTrain() {
		moduleNE = new SwerveModule(3, 2, abeNE, kP, kI, kD,true);
		moduleNW = new SwerveModule(1, 0, abeNW, kP, kI, kD,false);
		moduleSE = new SwerveModule(7, 6, abeSE, kP, kI, kD,true);
		moduleSW = new SwerveModule(5, 4, abeSW, kP, kI, kD,false);

		turning = new TurnModule(tkP, tkI, tkD);
	}
	
	public void setAllSpeed(double speed) {
		moduleNW.setDrive(speed);
		moduleNE.setDrive(speed);
		moduleSW.setDrive(speed);
		moduleSE.setDrive(speed);
			
	} 
	
	public void setEachSpeed(double sNW, double sNE, double sSW, double sSE) {
		moduleNW.setDrive(sNW);
		moduleNE.setDrive(sNE);
		moduleSW.setDrive(sSW);
		moduleSE.setDrive(sSE);
	}

	public void setAllDegrees(double deg) {


		moduleNW.setSteeringDeg(deg);
		moduleNE.setSteeringDeg(deg);
		moduleSW.setSteeringDeg(deg);
		moduleSE.setSteeringDeg(deg);
	}

	public void setEverything(double deg, double speed){
		double degNW = deg; 
		double speedNW = speed; 
		if (Math.abs(degNW - moduleNW.getSteeringEncoder() ) > 90 && Math.abs(degNW - moduleNW.getSteeringEncoder()) < 270) {
			degNW = ((int)degNW + 180) % 360;
			speedNW = -speedNW;
		}
		moduleNW.setDrive(speedNW);
		moduleNW.setSteeringDeg(degNW);

		double degNE = deg; 
		double speedNE = speed; 
		if (Math.abs(degNE - moduleNE.getSteeringEncoder() ) > 90 && Math.abs(degNE - moduleNE.getSteeringEncoder()) < 270) {
			degNE = ((int)degNE + 180) % 360;
			speedNE = -speedNE;
		}
		moduleNE.setDrive(speedNE);
		moduleNE.setSteeringDeg(degNE);

		double degSW = deg; 
		double speedSW = speed; 
		if (Math.abs(degSW - moduleSW.getSteeringEncoder() ) > 90 && Math.abs(degSW - moduleSW.getSteeringEncoder()) < 270) {
			degSW = ((int)degSW + 180) % 360;
			speedSW = -speedSW;
		}
		moduleSW.setDrive(speedSW);
		moduleSW.setSteeringDeg(degSW);

		double degSE = deg; 
		double speedSE = speed; 
		if (Math.abs(degSE - moduleSE.getSteeringEncoder() ) > 90 && Math.abs(degSE - moduleSE.getSteeringEncoder()) < 270) {
			degSE = ((int)degSE + 180) % 360;
			speedSE = -speedSE;
		}
		moduleSE.setDrive(speedSE);
		moduleSE.setSteeringDeg(degSE);
	}
	
	public void setEachDegree(double NE, double NW, double SE, double SW ) {
		moduleNE.setSteeringDeg(NE);
		moduleNW.setSteeringDeg(NW);
		moduleSE.setSteeringDeg(SE);
		moduleSW.setSteeringDeg(SW);
	}
	
	public double getSteeringSetpoint() { return moduleNW.getSteeringSetpoint(); }
	
	public double getSteeringError() { return moduleNW.getError(); }
	
	public double getSteeringPosition() { return moduleNW.getSteeringEncoder(); }
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SwerveDrive());
	}

}
