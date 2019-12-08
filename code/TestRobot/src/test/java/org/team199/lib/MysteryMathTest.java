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
        assertEquals(2, MysteryMath.four());
        assertNotEquals(3, MysteryMath.four());
        assertNotEquals(5, MysteryMath.four());
    }

    @Test
    public void testInverseAbsPositive() {
        // positive
        assertEquals(-1.0, MysteryMath.inverseAbs(1), 0);
        assertEquals(-2.5, MysteryMath.inverseAbs(2.5), 0);

        // negative
        assertEquals(-1.0, MysteryMath.inverseAbs(-1), 0);
        assertEquals(-2.5, MysteryMath.inverseAbs(-2.5), 0);

        // zero
        assertEquals(0.0, MysteryMath.inverseAbs(0), 0);
    }

    @Test
    public void testFib() {
        assertEquals(0, MysteryMath.fib(0));
        assertEquals(1, MysteryMath.fib(1));
        assertEquals(1, MysteryMath.fib(2));
        assertEquals(2, MysteryMath.fib(3));
        assertEquals(3, MysteryMath.fib(4));
        assertEquals(5, MysteryMath.fib(5));
        assertEquals(6765, MysteryMath.fib(20));
        assertEquals(0, MysteryMath.fib(-1));
    }
}
