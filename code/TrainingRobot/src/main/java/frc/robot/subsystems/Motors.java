/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Motors extends Subsystem {
  private WPI_TalonSRX talon;
  private VictorSP victor;
  private VictorSP victor2;

  private WPI_TalonSRX talon2;
  private VictorSP victor3;
  private VictorSP victor4;

  private Encoder lEncoder;
  private Encoder rEncoder;
  


  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Motors(WPI_TalonSRX talon,VictorSP victor,VictorSP victor2,WPI_TalonSRX talon2,VictorSP victor3,VictorSP victor4,Encoder lEncoder,Encoder rEncoder) {
    this.talon =talon;
    this.victor=victor;
    this.victor2=victor2;

    this.talon2 =talon2;
    this.victor3 =victor3;
    this.victor4=victor4;

    this.lEncoder=lEncoder;
    this.rEncoder=rEncoder;

    final double wheelDiameter=5; //in inches
    final double pulseFraction=1/256; //cool number i picked from robot2019 code. I have no idea wtf this means
    lEncoder.setDistancePerPulse(wheelDiameter*pulseFraction*Math.PI);
    rEncoder.setDistancePerPulse(wheelDiameter*pulseFraction*Math.PI);
  }

  public void run(double lSpeed, double rSpeed) {
    talon.set(-lSpeed);
    victor.set(-lSpeed);
    victor2.set(-lSpeed);

    talon2.set(rSpeed);
    victor3.set(rSpeed);
    victor4.set(rSpeed);

    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
