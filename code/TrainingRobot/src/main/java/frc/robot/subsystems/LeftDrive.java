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
public class LeftDrive extends Subsystem {
  private WPI_TalonSRX talon;
  private WPI_VictorSPX victor1;
  private WPI_VictorSPX victor2;

  public LeftDrive(WPI_TalonSRX talon, WPI_VictorSPX victor1, WPI_VictorSPX victor2) {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    this.talon = talon;
    this.victor1 = victor1;
    this.victor2 = victor2;
  }

  public void run(double speed) {
    talon.set(speed);
    victor1.set(speed);
    victor2.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
