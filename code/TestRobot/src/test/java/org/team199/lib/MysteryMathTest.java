/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team199.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Add your docs here.
 */
public class MysteryMathTest {
    @Test
    public void testFour() {
        assertEquals(MysteryMath.four(), 2);
        assertNotEquals(MysteryMath.four(), 3);
        assertNotEquals(MysteryMath.four(), 5);
    }

    @Test
    public void testInverseAbsPositive() {
        // positive
        assertEquals(MysteryMath.inverseAbs(1), -1.0, 0);
        assertEquals(MysteryMath.inverseAbs(2.5), -2.5, 0);

        // negative
        assertEquals(MysteryMath.inverseAbs(-1), -1.0, 0);
        assertEquals(MysteryMath.inverseAbs(-2.5), -2.5, 0);

        // zero
        assertEquals(MysteryMath.inverseAbs(0), 0.0, 0);
    }

    @Test
    public void testFib() {
        assertEquals(MysteryMath.fib(0), 0);
        assertEquals(MysteryMath.fib(1), 1);
        assertEquals(MysteryMath.fib(2), 1);
        assertEquals(MysteryMath.fib(3), 2);
        assertEquals(MysteryMath.fib(4), 3);
        assertEquals(MysteryMath.fib(5), 5);
        assertEquals(MysteryMath.fib(20), 6765);
        assertEquals(MysteryMath.fib(-1), 0);
    }
}
