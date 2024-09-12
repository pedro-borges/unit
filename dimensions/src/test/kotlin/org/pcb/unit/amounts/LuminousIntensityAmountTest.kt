package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.amounts.base.Numeric
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
            expected = Numeric(amount = 1),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1) /
                     LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY1))
    }

    @Test
    fun divOtherLuminousIntensity()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = LuminousIntensityAmount(amount = 2,
                                             unit = LUMINOUSINTENSITY2) /
                     LuminousIntensityAmount(amount = 20,
                                             unit = LUMINOUSINTENSITY1))
    }
}