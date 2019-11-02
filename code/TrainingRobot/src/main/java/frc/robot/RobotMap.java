/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    static WPI_TalonSRX talon;
    static WPI_TalonSRX talon2;
    static VictorSP victor;
    
    static WPI_TalonSRX talon3;
    static WPI_TalonSRX talon4;
    static VictorSP victor2;
    
    static Encoder lEncoder;
    static Encoder rEncoder;

    static {
        talon = new WPI_TalonSRX(8);
        talon2 = new WPI_TalonSRX(9);
        victor= new VictorSP(10);

        talon3 = new WPI_TalonSRX(5);
        talon4 = new WPI_TalonSRX(6);
        victor2= new VictorSP(7);

        lEncoder=new Encoder(0,1);
        rEncoder=new Encoder(2,3);
        final double wheelDiameter=5; //in inches
        final double pulseFraction=1/256; //cool number i picked from robot2019 code. I have no idea wtf this means
        lEncoder.setDistancePerPulse(wheelDiameter*pulseFraction*Math.PI);
        rEncoder.setDistancePerPulse(wheelDiameter*pulseFraction*Math.PI);



        
    }
}
