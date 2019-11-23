/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Motors;

public class Forward extends Command {
  private Motors motors;
  public Forward(Motors motors) {
    requires(this.motors = motors);
  }

  @Override
  protected void execute() {
    motors.run(-0.5,-0.5);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    motors.run(0,0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
