package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

public class SwitchModes extends InstantCommand{
    private Drivetrain drivetrain;
    public SwitchModes(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
}
protected void initialize() {
    if (drivetrain.tank()){
        drivetrain.tankvalue(false);
    } else{ 
        drivetrain.tankvalue(true);
    }
}
}
