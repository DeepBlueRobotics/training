/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    static WPI_TalonSRX lefttalon;
    static WPI_VictorSPX leftVictorSPX1;
    static WPI_VictorSPX leftVictorSPX2;

    static WPI_TalonSRX righttalon;
    static WPI_VictorSPX rightVictorSPX1;
    static WPI_VictorSPX rightVictorSPX2;

    static {
        lefttalon = new WPI_TalonSRX(1);
        leftVictorSPX1 = new WPI_VictorSPX(3);
        leftVictorSPX2 = new WPI_VictorSPX(9);

        righttalon = new WPI_TalonSRX(1);
        rightVictorSPX1 = new WPI_VictorSPX(3);
        rightVictorSPX2 = new WPI_VictorSPX(9);



    }
}
