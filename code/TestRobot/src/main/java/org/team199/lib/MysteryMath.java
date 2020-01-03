/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team199.lib;

/**
 * DON'T LOOK AT THIS CODE UNTIL YOU'VE RUN THE TESTS!!!
 */
public class MysteryMath {
    /**
     * @return what do you think it returns?
     */
    public static int four() {
        return 4;
    }

    /**
     * @return the negative of the absolute value
     */
    public static double inverseAbs(double input) {
        if (input < 0)
            input = Math.abs(input);
        
        return input * -1;
    }

    /**
     * Fibonacci, implemented with dynamic programming.
     * @return the number in the fib sequence, or zero if outside the range of the sequence
     */
    public static int fib(int num) {
        if (num < 1)
            return 0;

        int prev = 0, curr = 1, tmp;
        for (int i = 1; i < num; i++) {
            tmp = curr;
            curr += prev;
            prev = tmp;
        }

        return curr;
    }
}
