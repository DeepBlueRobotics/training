/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.RunMotor;
import frc.robot.subsystems.Motors;
import frc.robot.commands.RunMotorsWithJoystick;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    

    OI(Motors motors) {
        Joystick leftJoy = new Joystick(0);
        JoystickButton jb = new JoystickButton(leftJoy, 0);
        jb.whileHeld(new RunMotorsWithJoystick(motors, leftJoy));
        motors.setDefaultCommand(new RunMotorsWithJoystick(motors, leftJoy));
        //runMotorButton = new JoystickButton(controller, 1);
        //runMotorButton.whenPressed(new RunMotor(motors));
    }
}
