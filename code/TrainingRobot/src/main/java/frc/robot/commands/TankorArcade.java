package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

public class TankorArcade extends InstantCommand {
    Drivetrain motors;

    public TankorArcade(Drivetrain motors){
        this.motors = motors;

    }

    @Override
  protected void initialize() {
      if (motors.arcadeMode()){
          motors.setArcadeMode(false);
      }
      else {
          motors.setArcadeMode(true);
      }
  }
}