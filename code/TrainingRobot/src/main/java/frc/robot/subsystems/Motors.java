/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Motors extends Subsystem {
  private WPI_TalonSRX talon;
  private WPI_VictorSPX victor;
  private VictorSP victor2;

  private WPI_TalonSRX talon2;
  private WPI_VictorSPX victor3;
  private VictorSP victor4;




  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Motors(WPI_TalonSRX talon,WPI_VictorSPX victor,VictorSP victor2,WPI_TalonSRX talon2,WPI_VictorSPX victor3,VictorSP victor4) {
    this.talon =talon;
    this.victor=victor;
    this.victor2=victor2;

    this.talon2 =talon2;
    this.victor3 =victor3;
    this.victor4=victor4;

    
    //hopefully this works
  }

  public void run(double lSpeed, double rSpeed) {
    talon.set(lSpeed);
    victor.set(lSpeed);
    victor2.set(lSpeed);

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
