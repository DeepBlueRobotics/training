/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class SwitchDrivemode extends InstantCommand {
  /**
   * Add your docs here.
   */
  private Drivetrain dt;
  private Joystick leftJoy, rightJoy;
  private static Boolean tank = false;

  public SwitchDrivemode(Drivetrain dt, Joystick leftJoy, Joystick rightJoy) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.dt = dt;
    this.leftJoy = leftJoy;
    this.rightJoy = rightJoy;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    tank = !tank;
    if (tank) {
      dt.setDefaultCommand(new RunDrivetrainTank(leftJoy, rightJoy, dt));
      System.out.println("tank mode");
    } else {
      dt.setDefaultCommand(new RunDrivetrainArcade(leftJoy, rightJoy, dt));
      System.out.println("arcade mode");
    }
  }

}
