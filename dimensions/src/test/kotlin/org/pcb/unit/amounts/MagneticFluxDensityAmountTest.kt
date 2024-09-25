package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MagneticFluxDensityAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 2 * 2,
                                                 unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                                 unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                                 unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxDensityAmount(amount = 0,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)) +
                     MagneticFluxDensityAmount(amount = 1,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun plusOtherMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 1,
                                                 unit = MASS2 to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxDensityAmount(amount = 0,
                                               unit = MASS2 to U2(ELECTRICCURRENT1, TIME1)) +
                     MagneticFluxDensityAmount(amount = 10000,
                                               unit = MASS1 to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun minusSameMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 0,
                                                 unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxDensityAmount(amount = 1,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)) -
                     MagneticFluxDensityAmount(amount = 1,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun minusOtherMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxDensityAmount(amount = 0,
                                                 unit = MASS2 to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxDensityAmount(amount = 1,
                                               unit = MASS2 to U2(ELECTRICCURRENT1, TIME1)) -
                     MagneticFluxDensityAmount(amount = 10000,
                                               unit = MASS1 to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameMagneticFluxDensity()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)) /
                     MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherMagneticFluxDensity()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS2 to U2(ELECTRICCURRENT1, TIME1)) /
                     MagneticFluxDensityAmount(amount = 20000,
                                               unit = MASS1 to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesArea()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(ELECTRICCURRENT1, TIME1)) *
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }
}