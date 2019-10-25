/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    static WPI_TalonSRX talon;
    static WPI_VictorSPX talon2;
    static VictorSP victor;
    
    static WPI_TalonSRX talon3;
    static WPI_VictorSPX talon4;
    static VictorSP victor2;
    

    static {
        talon = new WPI_TalonSRX(8);
        talon2 = new WPI_VictorSPX(9);
        victor= new VictorSP(10);

        talon3 = new WPI_TalonSRX(5);
        talon4 = new WPI_VictorSPX(6);
        victor2= new VictorSP(7);


        
    }
}
