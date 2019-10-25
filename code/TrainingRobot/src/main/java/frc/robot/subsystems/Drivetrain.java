/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  private SpeedController LTalon1, LTalon2, RTalon1, RTalon2, LVictor1, RVictor1;
  private Encoder encoder;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Drivetrain(SpeedController LTalon1, SpeedController LTalon2, SpeedController LVictor1, SpeedController RTalon1, SpeedController RTalon2, SpeedController RVictor1, Encoder encoder) {
    this.LTalon1 = LTalon1;
    this.LTalon2 = LTalon2;
    this.LVictor1 = LVictor1;
    this.RTalon1 = RTalon1;
    this.RTalon2 = RTalon2;
    this.RVictor1 = RVictor1;
    this.encoder = encoder;
    encoder.setDistancePerPulse(1./256.); // Set to actual number
  }

  public void run(double LSpeed, double RSpeed) {
    LTalon1.set(-LSpeed);
    LTalon2.set(-LSpeed);
    LVictor1.set(-LSpeed);
    RTalon1.set(RSpeed);
    RTalon2.set(RSpeed);
    RVictor1.set(RSpeed);
  }

  public void runDistance(double dist) {
    if (encoder.getDistance() < dist)
      run(0.5, 0.5);
    else
      run(0, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}