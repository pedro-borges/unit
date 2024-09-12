package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.ANGLE1
import org.pcb.unit.units.test.ANGLE2
import org.pcb.unit.units.test.LUMINOUSINTENSITY1
import org.pcb.unit.units.test.LUMINOUSINTENSITY2
import org.pcb.unit.units.test.NUMBER2

class LuminousFluxAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 2 * 2,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1)),
            actual = LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 1,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1)),
            actual = LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameLuminousFlux()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 1,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1)),
            actual = LuminousFluxAmount(amount = 0,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)) +
                     LuminousFluxAmount(amount = 1,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)))
    }

    @Test
    fun plusOtherLuminousFlux()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 1,
                                          unit = U2(LUMINOUSINTENSITY2, ANGLE2)),
            actual = LuminousFluxAmount(amount = 0,
                                        unit = U2(LUMINOUSINTENSITY2, ANGLE2)) +
                     LuminousFluxAmount(amount = 1000,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)))
    }

    @Test
    fun minusSameLuminousFlux()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 0,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1)),
            actual = LuminousFluxAmount(amount = 1,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)) -
                     LuminousFluxAmount(amount = 1,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)))
    }

    @Test
    fun minusOtherLuminousFlux()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 0,
                                          unit = U2(LUMINOUSINTENSITY2, ANGLE2)),
            actual = LuminousFluxAmount(amount = 1,
                                        unit = U2(LUMINOUSINTENSITY2, ANGLE2)) -
                     LuminousFluxAmount(amount = 1000,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)))
    }

    @Test
    fun divSameLuminousFlux()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)) /
                     LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)))
    }

    @Test
    fun divOtherLuminousFlux()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY2, ANGLE2)) /
                     LuminousFluxAmount(amount = 2000,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)))
    }

    @Test
    fun divSameLuminousIntensity()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 1,
                                        unit = ANGLE1),
            actual = LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)) /
                     LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun divOtherLuminousIntensity()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 1,
                                        unit = ANGLE1),
            actual = LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY2, ANGLE1)) /
                     LuminousIntensityAmount(amount = 20,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun divSameSolidAngle()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 1,
                                               unit = LUMINOUSINTENSITY1),
            actual = LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1)) /
                     SolidAngleAmount(amount = 2,
                                      unit = ANGLE1))
    }

    @Test
    fun divOtherSolidAngle()
    {
        assertEquals(
            expected = LuminousIntensityAmount(amount = 1,
                                               unit = LUMINOUSINTENSITY1),
            actual = LuminousFluxAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE2)) /
                     SolidAngleAmount(amount = 200,
                                      unit = ANGLE1))
    }
}