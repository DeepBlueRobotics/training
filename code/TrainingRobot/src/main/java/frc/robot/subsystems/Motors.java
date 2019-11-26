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
 

/**
 * Add your docs here.
 */
public class Motors extends Subsystem {
  private WPI_TalonSRX talon;
  private WPI_VictorSPX VictorSPX;
  private WPI_VictorSPX VictorSPX2;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Motors(WPI_TalonSRX talon, WPI_VictorSPX VictorSPX, WPI_VictorSPX VictorSPX2) {
    this.talon = talon;
    this.VictorSPX = VictorSPX;
    this.VictorSPX2 = VictorSPX2;
  }

  public void tankdrive(double speed) {
    talon.set(speed);
    VictorSPX.set(speed);
    VictorSPX2.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
