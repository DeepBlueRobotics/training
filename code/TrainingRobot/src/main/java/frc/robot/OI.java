/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.RunMotorsWithJoystick;
import frc.robot.subsystems.Motors;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick leftJoy, rightJoy, controller;

    OI(Motors motors) {
        leftJoy = new Joystick(0);
        rightJoy = new Joystick(1);
        controller = new Joystick(2);

        motors.setDefaultCommand(new RunMotorsWithJoystick(motors, leftJoy));
    }
}
