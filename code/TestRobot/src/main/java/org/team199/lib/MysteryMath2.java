/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team199.lib;

public class MysteryMath2 {
    /**
     * Finds the larger x that satisfies ax^2 + bx + c = 0
     * 
     * @param a coefficient of x^2
     * @param b coefficient of x
     * @param c constant
     * @return the larger of the two solutions to a quadratic equation, or -1 if there are no solutions
     */
    public static double quadraticFormula(double a, double b, double c) {
        return (-b + Math.sqrt(b*b - 4*a*c)) / (2*a);
    }
}
