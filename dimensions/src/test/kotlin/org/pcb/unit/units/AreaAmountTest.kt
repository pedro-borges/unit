package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.TIME1

class AreaAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = DISTANCE1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE1),
            actual = AreaAmount(amount = 0,
                                unit = DISTANCE1) +
                     AreaAmount(amount = 1,
                                unit = DISTANCE1))
    }

    @Test
    fun plusOtherArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = DISTANCE2),
            actual = AreaAmount(amount = 0,
                                unit = DISTANCE2) +
                     AreaAmount(amount = 100,
                                unit = DISTANCE1))
    }

    @Test
    fun minusSameArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 0,
                                  unit = DISTANCE1),
            actual = AreaAmount(amount = 1,
                                unit = DISTANCE1) -
                     AreaAmount(amount = 1,
                                unit = DISTANCE1))
    }

    @Test
    fun minusOtherArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 0,
                                  unit = DISTANCE2),
            actual = AreaAmount(amount = 1,
                                unit = DISTANCE2) -
                     AreaAmount(amount = 100,
                                unit = DISTANCE1))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) /
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE2) /
                     AreaAmount(amount = 200,
                                unit = DISTANCE1))
    }

    @Test
    fun divSameDistance()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) /
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun divOtherDistance()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE2),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE2) /
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesSameDistance()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = DISTANCE1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesOtherDistance()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = DISTANCE2),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE2) *
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesMagneticFluxDensity()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     MagneticFluxDensityAmount(amount = 2,
                                               unit = MASS1 to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesSamePressure()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     PressureAmount(amount = 2,
                                    unit = MASS1 to U2(DISTANCE1, TIME1)))
    }

    @Test
    fun timesOtherPressure()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = AreaAmount(amount = 2,
                                unit = DISTANCE1) *
                     PressureAmount(amount = 20,
                                    unit = MASS1 to U2(DISTANCE2, TIME1)))
    }
}