/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Motors extends Subsystem {
  private WPI_TalonSRX talon;
  private WPI_TalonSRX talon2;
  private VictorSP victor;

  private WPI_TalonSRX talon3;
  private WPI_TalonSRX talon4;
  private VictorSP victor2;




  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Motors(WPI_TalonSRX talon,WPI_TalonSRX talon2,VictorSP victor,WPI_TalonSRX talon3,WPI_TalonSRX talon4,VictorSP victor2) {
    this.talon =talon;
    this.talon2=talon2;
    this.victor=victor;

    this.talon3 =talon3;
    this.talon4 =talon4;
    this.victor2=victor2;

    
    //hopefully this works
  }

  public void run(double lSpeed, double rSpeed) {
    talon.set(lSpeed);
    talon2.set(lSpeed);
    victor.set(lSpeed);

    talon3.set(rSpeed);
    talon4.set(rSpeed);
    victor2.set(rSpeed);

    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
