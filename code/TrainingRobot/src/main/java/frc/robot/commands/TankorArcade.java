package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

public class TankorArcade extends InstantCommand {
    DriveTrain motors;

    public TankorArcade(DriveTrain motors){
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