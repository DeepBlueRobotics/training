/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.TankorArcade;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
/**import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.RunMotor;
import frc.robot.commands.RunMotorsWithJoystick;**/
import frc.robot.subsystems.DriveTrain;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick leftJoy, rightJoy, controller;
    
    /*private JoystickButton runMotorButton;*/
    private JoystickButton tankorArcadeButton;
    OI(DriveTrain motors) {
        leftJoy = new Joystick(0);
        rightJoy = new Joystick(1);
        controller = new Joystick(2);
        
        tankorArcadeButton = new JoystickButton(controller,2);
        tankorArcadeButton.whenPressed(new TankorArcade(motors));

    }
}
