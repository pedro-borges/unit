package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.units.test.ANGLE1
import org.pcb.unit.units.test.ANGLE2
import org.pcb.unit.units.test.LUMINOUSINTENSITY1
import org.pcb.unit.units.test.LUMINOUSINTENSITY2
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class LuminousEnergyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LuminousEnergyAmount(amount = 2 * 2,
                                            unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)),
            actual = LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LuminousEnergyAmount(amount = 1,
                                            unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)),
            actual = LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameLuminousEnergy()
    {
        assertEquals(
            expected = LuminousEnergyAmount(amount = 1,
                                            unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)),
            actual = LuminousEnergyAmount(amount = 0,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)) +
                     LuminousEnergyAmount(amount = 1,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)))
    }

    @Test
    fun plusOtherLuminousEnergy()
    {
        assertEquals(
            expected = LuminousEnergyAmount(amount = 1,
                                            unit = U3(ANGLE2, LUMINOUSINTENSITY2, TIME2)),
            actual = LuminousEnergyAmount(amount = 0,
                                          unit = U3(ANGLE2, LUMINOUSINTENSITY2, TIME2)) +
                     LuminousEnergyAmount(amount = 10000,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)))
    }

    @Test
    fun minusSameLuminousEnergy()
    {
        assertEquals(
            expected = LuminousEnergyAmount(amount = 0,
                                            unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)),
            actual = LuminousEnergyAmount(amount = 1,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)) -
                     LuminousEnergyAmount(amount = 1,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)))
    }

    @Test
    fun minusOtherLuminousEnergy()
    {
        assertEquals(
            expected = LuminousEnergyAmount(amount = 0,
                                            unit = U3(ANGLE2, LUMINOUSINTENSITY2, TIME2)),
            actual = LuminousEnergyAmount(amount = 1,
                                          unit = U3(ANGLE2, LUMINOUSINTENSITY2, TIME2)) -
                     LuminousEnergyAmount(amount = 10000,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)))
    }

    @Test
    fun divSameLuminousEnergy()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)) /
                     LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)))
    }

    @Test
    fun divOtherLuminousEnergy()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE2, LUMINOUSINTENSITY2, TIME2)) /
                     LuminousEnergyAmount(amount = 20000,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 1,
                                          unit = U2(ANGLE1, LUMINOUSINTENSITY1)),
            actual = LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = LuminousFluxAmount(amount = 1,
                                          unit = U2(ANGLE1, LUMINOUSINTENSITY1)),
            actual = LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME2)) /
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameLuminousFlux()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE1, LUMINOUSINTENSITY1, TIME1)) /
                     LuminousFluxAmount(amount = 2,
                                        unit = U2(ANGLE1, LUMINOUSINTENSITY1)))
    }

    @Test
    fun divOtherLuminousFlux()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = LuminousEnergyAmount(amount = 2,
                                          unit = U3(ANGLE2, LUMINOUSINTENSITY2, TIME1)) /
                     LuminousFluxAmount(amount = 2000,
                                        unit = U2(ANGLE1, LUMINOUSINTENSITY1)))
    }
}