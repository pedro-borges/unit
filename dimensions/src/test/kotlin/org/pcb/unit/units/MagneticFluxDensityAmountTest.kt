package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.MagneticFluxDensityAmount
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MagneticFluxDensityAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 2 * 2,
                                          unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxDensityAmount(amount = 2,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                          unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxDensityAmount(amount = 2,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                          unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxDensityAmount(amount = 0,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)) +
                     MagneticFluxDensityAmount(amount = 1,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun plusOtherMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                          unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxDensityAmount(amount = 0,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)) +
                     MagneticFluxDensityAmount(amount = 1,
                                        unit = MASS2 to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun minusSameMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 0,
                                          unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxDensityAmount(amount = 1,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)) -
                     MagneticFluxDensityAmount(amount = 1,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun minusOtherMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 0,
                                          unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)),
            actual = MagneticFluxDensityAmount(amount = 2,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)) -
                     MagneticFluxDensityAmount(amount = 2,
                                        unit = MASS2 to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameMagneticFluxDensity()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MagneticFluxDensityAmount(amount = 2,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)) /
                     MagneticFluxDensityAmount(amount = 2,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherMagneticFluxDensity()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MagneticFluxDensityAmount(amount = 2,
                                        unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)) /
                     MagneticFluxDensityAmount(amount = 2,
                                        unit = MASS2 to U2(TIME2, ELECTRICCURRENT2)))
    }
}