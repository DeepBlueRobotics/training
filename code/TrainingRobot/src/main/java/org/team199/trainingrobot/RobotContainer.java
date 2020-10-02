package org.team199.trainingrobot;

import org.team199.trainingrobot.Constants;
import org.team199.trainingrobot.subsystems.Motors;
import org.team199.trainingrobot.commands.RunMotor;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
    private final Motors motors = new Motors();
    private final Joystick controller = new Joystick(Constants.OI.Controller.kPort);

    public RobotContainer() {
        configureButtonBindingsController();
        // set default commands here
    }
    private void configureButtonBindingsController() {
        new JoystickButton(controller, Constants.OI.Controller.kRunMotorsButton).whileHeld(new RunMotor(motors));
    }

    public Command getAutonomousCommand() {
        return new InstantCommand();
    }
}