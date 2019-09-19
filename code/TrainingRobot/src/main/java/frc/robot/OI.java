/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.RunMotor;
import frc.robot.commands.RunMotorsWithJoystick;
import frc.robot.commands.Drive;
import frc.robot.commands.SwapMode;
import frc.robot.subsystems.Drivetrain;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick leftJoy, rightJoy, controller;
    
    private JoystickButton swapButton;

    OI(Drivetrain drivetrain) {
        leftJoy = RobotMap.leftJoy;
        rightJoy = RobotMap.rightJoy;
        controller = RobotMap.controller;

        drivetrain.setDefaultCommand(new Drive(drivetrain, leftJoy, rightJoy));

        swapButton = new JoystickButton(controller, 2);
        swapButton.whenPressed(new SwapMode(drivetrain));
        //runMotorButton.whileHeld(new RunMotor(motors));
        //motors.setDefaultCommand(new RunMotorsWithJoystick(motors, leftJoy));
    }
}
