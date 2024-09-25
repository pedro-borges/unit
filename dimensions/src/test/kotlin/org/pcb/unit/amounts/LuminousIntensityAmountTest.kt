package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.units.test.ANGLE1
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.LUMINOUSINTENSITY1
import org.pcb.unit.units.test.LUMINOUSINTENSITY2
import org.pcb.unit.units.test.NUMBER2

class LuminousIntensityAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 2 * 2,
                                               unit = LUMINOUSINTENSITY1),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 1,
                                               unit = LUMINOUSINTENSITY1),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameLuminousIntensity()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 1,
                                               unit = LUMINOUSINTENSITY1),
            actual = LuminousIntensityAmount(amount = 0,
                                             unit = LUMINOUSINTENSITY1) +
                     LuminousIntensityAmount(amount = 1,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun plusOtherLuminousIntensity()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 1,
                                               unit = LUMINOUSINTENSITY2),
            actual = LuminousIntensityAmount(amount = 0,
                                             unit = LUMINOUSINTENSITY2) +
                     LuminousIntensityAmount(amount = 10,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun minusSameLuminousIntensity()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 0,
                                               unit = LUMINOUSINTENSITY1),
            actual = LuminousIntensityAmount(amount = 1,
                                             unit = LUMINOUSINTENSITY1) -
                     LuminousIntensityAmount(amount = 1,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun minusOtherLuminousIntensity()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 0,
                                               unit = LUMINOUSINTENSITY2),
            actual = LuminousIntensityAmount(amount = 1,
                                             unit = LUMINOUSINTENSITY2) -
                     LuminousIntensityAmount(amount = 10,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun divSameLuminousIntensity()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1) /
                     LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun divOtherLuminousIntensity()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY2) /
                     LuminousIntensityAmount(amount = 20,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun timesSolidAngle()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 2 * 2,
                                          unit = U2(ANGLE1, LUMINOUSINTENSITY1)),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1) *
                     SolidAngleAmount(amount = 2,
                                      unit = ANGLE1))
    }

    @Test
    fun divArea()
    {
        assertEquals(
            expected = LuminanceAmount(amount = 1,
                                       unit = LUMINOUSINTENSITY1 to DISTANCE1),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1) /
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun divSameLuminance()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1) /
                     LuminanceAmount(amount = 2,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1))
    }

    @Test
    fun divOtherLuminance()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY2) /
                     LuminanceAmount(amount = 20,
                                     unit = LUMINOUSINTENSITY1 to DISTANCE1))
    }
}