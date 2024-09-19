package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.LUMINOUSINTENSITY1
import org.pcb.unit.units.test.LUMINOUSINTENSITY2
import org.pcb.unit.units.test.NUMBER2

class LuminanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LuminanceAmount(amount = 2 * 2,
                                       unit = LUMINOUSINTENSITY1 to DISTANCE1),
            actual = LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LuminanceAmount(amount = 1,
                                       unit = LUMINOUSINTENSITY1 to DISTANCE1),
            actual = LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameLuminance()
    {
        assertEquals(
            expected = LuminanceAmount(amount = 1,
                                       unit = LUMINOUSINTENSITY1 to DISTANCE1),
            actual = LuminanceAmount(amount = 0,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1) +
                     LuminanceAmount(amount = 1,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1))
    }

    @Test
    fun plusOtherLuminance()
    {
        assertEquals(
            expected = LuminanceAmount(amount = 1,
                                       unit = LUMINOUSINTENSITY2 to DISTANCE1),
            actual = LuminanceAmount(amount = 0,
                                     unit = LUMINOUSINTENSITY2 to DISTANCE1) +
                     LuminanceAmount(amount = 1000,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE2))
    }

    @Test
    fun minusSameLuminance()
    {
        assertEquals(
            expected = LuminanceAmount(amount = 0,
                                       unit = LUMINOUSINTENSITY1 to DISTANCE1),
            actual = LuminanceAmount(amount = 1,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1) -
                     LuminanceAmount(amount = 1,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1))
    }

    @Test
    fun minusOtherLuminance()
    {
        assertEquals(
            expected = LuminanceAmount(amount = 0,
                                       unit = LUMINOUSINTENSITY2 to DISTANCE1),
            actual = LuminanceAmount(amount = 1,
                                     unit = LUMINOUSINTENSITY2 to DISTANCE1) -
                     LuminanceAmount(amount = 1000,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE2))
    }

    @Test
    fun divSameLuminance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1) /
                     LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1))
    }

    @Test
    fun divOtherLuminance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY2 to DISTANCE1) /
                     LuminanceAmount(amount = 2000,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE2))
    }

    @Test
    fun timesSameArea()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 2 * 2,
                                               unit = LUMINOUSINTENSITY1),
            actual = LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1) *
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun timesOtherArea()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 2 * 2,
                                               unit = LUMINOUSINTENSITY1),
            actual = LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE2) *
                     AreaAmount(amount = 200,
                                unit = DISTANCE1))
    }
}