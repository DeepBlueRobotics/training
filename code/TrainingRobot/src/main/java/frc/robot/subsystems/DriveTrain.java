/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX lefttalon;
  private WPI_VictorSPX leftVictorSPX1;
  private WPI_VictorSPX leftVictorSPX2;

  private WPI_TalonSRX righttalon;
  private WPI_VictorSPX rightVictorSPX1;
  private WPI_VictorSPX rightVictorSPX2;

  private Encoder leftEnc, rightEnc;

  private  boolean arcadeMode;
  public Drivetrain(WPI_TalonSRX lefttalon, WPI_VictorSPX leftVictorSPX1, WPI_VictorSPX leftVictorSPX2,WPI_TalonSRX righttalon, WPI_VictorSPX rightVictorSPX1, WPI_VictorSPX rightVictorSPX2, Encoder leftEnc, Encoder rightEnc) {
    this.lefttalon = lefttalon;
    this.leftVictorSPX1 = leftVictorSPX1;
    this.leftVictorSPX2 = leftVictorSPX2;

    this.righttalon = righttalon;
    this.rightVictorSPX1 = rightVictorSPX1;
    this.rightVictorSPX2 = rightVictorSPX2;

    this.leftEnc = leftEnc;
    this.rightEnc = rightEnc;
    arcadeMode = true;

    double pulseFraction = 1.0 / 256;
    double wheelDiameter = 5;
    leftEnc.setDistancePerPulse(pulseFraction * Math.PI * wheelDiameter);
    rightEnc.setDistancePerPulse(pulseFraction * Math.PI * wheelDiameter);
    leftEnc.reset();
    rightEnc.reset();
    rightEnc.setReverseDirection(true);  
  }

  public boolean arcadeMode(){
    return arcadeMode;
  }

  public void setArcadeMode(boolean arcadeMode){
    this.arcadeMode = arcadeMode;
  }
  
  public void run(double leftspeed,double rightspeed) {
    lefttalon.set(-leftspeed);
    leftVictorSPX1.set(-leftspeed);
    leftVictorSPX2.set(-leftspeed);
    System.out.println("Tank mode left: " + lefttalon.get());

    righttalon.set(rightspeed);
    rightVictorSPX1.set(rightspeed);
    rightVictorSPX2.set(rightspeed);
    System.out.println("Tank mode right: " + righttalon.get());
  }

  public void arcaderun(double xspeed, double zrotation) {
    lefttalon.set(-xspeed + zrotation);
    leftVictorSPX1.set(-xspeed + zrotation);
    leftVictorSPX2.set(-xspeed + zrotation);
    System.out.println("Arcade mode left: " + lefttalon.get());

    righttalon.set(xspeed + zrotation);
    rightVictorSPX1.set(xspeed + zrotation);
    rightVictorSPX2.set(xspeed + zrotation);
    System.out.println("Arcade mode right: " + righttalon.get());
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
