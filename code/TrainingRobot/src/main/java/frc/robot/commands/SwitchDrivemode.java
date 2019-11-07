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
import frc.robot.commands.RunDrivetrain;

/**
 * Add your docs here.
 */
public class SwitchDrivemode extends InstantCommand {
  /**
   * Add your docs here.
   */
  private Joystick joystick1, joystick2;
  private Drivetrain dt;
  public SwitchDrivemode(Joystick joystick1, Joystick joystick2, Drivetrain dt) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(this.dt = dt);
    this.joystick1 = joystick1;
    this.joystick2 = joystick2;

  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    (new RunDrivetrain(joystick1, joystick2, dt)).switchMode();
  }

}
