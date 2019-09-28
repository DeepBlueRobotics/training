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
public class Drivetrain extends Subsystem {
  private WPI_TalonSRX LTalon1, LTalon2, RTalon1, RTalon2;
  private WPI_VictorSPX LVictor1, RVictor1;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Drivetrain(WPI_TalonSRX LTalon1, WPI_TalonSRX LTalon2, WPI_VictorSPX LVictor1, WPI_TalonSRX RTalon1, WPI_TalonSRX RTalon2, WPI_VictorSPX RVictor1) {
    this.LTalon1 = LTalon1;
    this.LTalon2 = LTalon2;
    this.LVictor1 = LVictor1;
    this.RTalon1 = RTalon1;
    this.RTalon2 = RTalon2;
    this.RVictor1 = RVictor1;
  }

  public void run(double LSpeed, double RSpeed) {
    LTalon1.set(LSpeed);
    LTalon2.set(LSpeed);
    LVictor1.set(LSpeed);
    RTalon1.set(RSpeed);
    RTalon2.set(RSpeed);
    RVictor1.set(RSpeed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
