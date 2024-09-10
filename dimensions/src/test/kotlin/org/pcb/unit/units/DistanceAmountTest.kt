package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class DistanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = DistanceAmount(amount = 2 * 2,
                                      unit = DISTANCE1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameDistance()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE1),
            actual = DistanceAmount(amount = 0,
                                    unit = DISTANCE1) +
                     DistanceAmount(amount = 1,
                                    unit = DISTANCE1))
    }

    @Test
    fun plusOtherDistance()
    {
        assertEquals(
            expected = DistanceAmount(amount = 1,
                                      unit = DISTANCE2),
            actual = DistanceAmount(amount = 0,
                                    unit = DISTANCE2) +
                     DistanceAmount(amount = 10,
                                    unit = DISTANCE1))
    }

    @Test
    fun minusSameDistance()
    {
        assertEquals(
            expected = DistanceAmount(amount = 0,
                                      unit = DISTANCE1),
            actual = DistanceAmount(amount = 1,
                                    unit = DISTANCE1) -
                     DistanceAmount(amount = 1,
                                    unit = DISTANCE1))
    }

    @Test
    fun minusOtherDistance()
    {
        assertEquals(
            expected = DistanceAmount(amount = 0, DISTANCE2),
            actual = DistanceAmount(amount = 1,
                                    unit = DISTANCE2) -
                     DistanceAmount(amount = 10,
                                    unit = DISTANCE1))
    }

    @Test
    fun divSameDistance()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE1) /
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun divOtherDistance()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE2) /
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE1 to TIME1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesSameDistance()
    {
        assertEquals(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = DISTANCE1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE1) *
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesOtherDistance()
    {
        assertEquals(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = DISTANCE2),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE2) *
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesSameArea()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = DISTANCE1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE1) *
                     AreaAmount(amount = 2,
                                unit = DISTANCE1))
    }

    @Test
    fun timesOtherArea()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = DISTANCE2),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE2) *
                     AreaAmount(amount = 200,
                                unit = DISTANCE1))
    }

    @Test
    fun timesSameForce()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE1) *
                     ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun timesOtherForce()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, DISTANCE2) to TIME2),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE2) *
                     ForceAmount(amount = 20,
                                 unit = U2(MASS2, DISTANCE1) to TIME2))
    }

    @Test
    fun timesMassFlux()
    {
        assertEquals(
            expected = MomentumAmount(amount = 2 * 2,
                                      unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = DistanceAmount(amount = 2,
                                    unit = DISTANCE1) *
                     MassFluxAmount(amount = 2,
                                    unit = MASS1 to TIME1))
    }
}
