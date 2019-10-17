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
  private WPI_VictorSPX VictorSPX;
  private VictorSP VictorSP;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Motors(WPI_TalonSRX talon, WPI_VictorSPX VictorSPX, VictorSP VictorSP) {
    this.talon = talon;
    this.VictorSPX = VictorSPX;
    this.VictorSP = VictorSP;
  }

  public void run(double speed) {
    talon.set(speed);
    VictorSPX.set(speed);
    VictorSP.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
