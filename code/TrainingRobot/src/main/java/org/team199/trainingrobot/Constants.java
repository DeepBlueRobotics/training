/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team199.trainingrobot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * Constants for motor ports and IDs, solenoid ports, sensor numbers, etc.
     * Analagous to RobotMap.java in previous years.
     * 
     * Put other constants (like motor speed and characterization constants) in the
     * subsystems themselves.
     */
    public static final class Drive {
        public static final int kTalon = 1;
    }

    /**
     * Constants for controllers and joysticks. Analagous to OI.java in previous
     * years.
     */
    public static final class OI {
        public static final class Controller {
            public static final int kPort = 2;

            // Buttons and triggers
            public static final int X = 1;
            public static final int A = 2;
            public static final int B = 3;
            public static final int Y = 4;
            public static final int LB = 5;
            public static final int RB = 6;
            public static final int LT = 7;
            public static final int RT = 8;
            public static final int BACK = 9;
            public static final int START = 10;

            public static final int kRunMotorsButton = X;
        }
    }
}