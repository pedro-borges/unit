package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.ANGLE1
import org.pcb.unit.units.test.ANGLE2
import org.pcb.unit.units.test.LUMINOUSINTENSITY1
import org.pcb.unit.units.test.NUMBER2

class SolidAngleAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 2 * 2,
                                        unit = ANGLE1),
            actual = SolidAngleAmount(amount = 2,
                                      unit = ANGLE1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 1,
                                        unit = ANGLE1),
            actual = SolidAngleAmount(amount = 2,
                                      unit = ANGLE1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameSolidAngle()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 1,
                                        unit = ANGLE1),
            actual = SolidAngleAmount(amount = 0,
                                      unit = ANGLE1) +
                     SolidAngleAmount(amount = 1,
                                      unit = ANGLE1))
    }

    @Test
    fun plusOtherSolidAngle()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 1,
                                        unit = ANGLE2),
            actual = SolidAngleAmount(amount = 0,
                                      unit = ANGLE2) +
                     SolidAngleAmount(amount = 100,
                                      unit = ANGLE1))
    }

    @Test
    fun minusSameSolidAngle()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 0,
                                        unit = ANGLE1),
            actual = SolidAngleAmount(amount = 1,
                                      unit = ANGLE1) -
                     SolidAngleAmount(amount = 1,
                                      unit = ANGLE1))
    }

    @Test
    fun minusOtherSolidAngle()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 0,
                                        unit = ANGLE2),
            actual = SolidAngleAmount(amount = 1,
                                      unit = ANGLE2) -
                     SolidAngleAmount(amount = 100,
                                      unit = ANGLE1))
    }

    @Test
    fun divSameSolidAngle()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = SolidAngleAmount(amount = 2,
                                      unit = ANGLE1) /
                     SolidAngleAmount(amount = 2,
                                      unit = ANGLE1))
    }

    @Test
    fun divOtherSolidAngle()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = SolidAngleAmount(amount = 2,
                                      unit = ANGLE2) /
                     SolidAngleAmount(amount = 200,
                                      unit = ANGLE1))
    }

    @Test
    fun timesLuminousIntensity()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 2 * 2,
                                          unit = U2(ANGLE1, LUMINOUSINTENSITY1)),
            actual = SolidAngleAmount(amount = 2,
                                      unit = ANGLE1) *
                     LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1))
    }
}