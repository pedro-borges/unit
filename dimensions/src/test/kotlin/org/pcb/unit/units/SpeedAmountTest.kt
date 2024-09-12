package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.base.Numeric
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER1
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class SpeedAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 * 2,
                                   unit = DISTANCE1 to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE1 to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE1 to TIME1),
            actual = SpeedAmount(amount = 0,
                                 unit = DISTANCE1 to TIME1) +
                     SpeedAmount(amount = 1,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun plusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE2 to TIME1),
            actual = SpeedAmount(amount = 0,
                                 unit = DISTANCE2 to TIME1) +
                     SpeedAmount(amount = 100,
                                 unit = DISTANCE1 to TIME2))
    }

    @Test
    fun minusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 0,
                                   unit = DISTANCE1 to TIME1),
            actual = SpeedAmount(amount = 1,
                                 unit = DISTANCE1 to TIME1) -
                     SpeedAmount(amount = 1,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun minusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 0,
                                   unit = DISTANCE2 to TIME1),
            actual = SpeedAmount(amount = 1,
                                 unit = DISTANCE2 to TIME1) -
                     SpeedAmount(amount = 100,
                                 unit = DISTANCE1 to TIME2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE2 to TIME1) /
                     SpeedAmount(amount = 200,
                                 unit = DISTANCE1 to TIME2))
    }

    @Test
    fun timesSamePace()
    {
        assertEquals(
            expected = Numeric(amount = 2 * 2),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) *
                     PaceAmount(amount = 2,
                                unit = TIME1 to DISTANCE1))
    }

    @Test
    fun timesOtherPace()
    {
        assertEquals(
            expected = Numeric(amount = 2 * 2),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME2) *
                     PaceAmount(amount = 200,
                                unit = TIME1 to DISTANCE2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = DistanceAmount(amount = 2 * 2,
                                      unit = DISTANCE1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = DistanceAmount(amount = 2 * 2,
                                      unit = DISTANCE1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE2 to TIME2),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE2 to TIME2) /
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameDistance()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMBER1 to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) /
                     DistanceAmount(amount = 2,
                                    unit = DISTANCE1))
    }

    @Test
    fun divOtherDistance()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMBER1 to TIME2),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE2 to TIME2) /
                     DistanceAmount(amount = 20,
                                    unit = DISTANCE1))
    }

    @Test
    fun timesSameMomentum()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) *
                     MomentumAmount(amount = 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun timesOtherMomentum()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, DISTANCE2) to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE2 to TIME1) *
                     MomentumAmount(amount = 200,
                                    unit = U2(MASS2, DISTANCE1) to TIME2))
    }

    @Test
    fun timesSameForce()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1) *
                     ForceAmount(amount = 2,
                                 unit = U2(MASS1, DISTANCE1) to TIME1))
    }

    @Test
    fun timesOtherForce()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(MASS2, DISTANCE2) to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = DISTANCE2 to TIME1) *
                     ForceAmount(amount = 2000,
                                 unit = U2(MASS2, DISTANCE1) to TIME2))
    }
}
