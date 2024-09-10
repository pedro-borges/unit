package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class AccelerationAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 2 * 2,
                                          unit = DISTANCE1 to TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = AccelerationAmount(amount = 0,
                                        unit = DISTANCE1 to TIME1) +
                     AccelerationAmount(amount = 1,
                                        unit = DISTANCE1 to TIME1))
    }

    @Test
    fun plusOtherAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = AccelerationAmount(amount = 0,
                                        unit = DISTANCE1 to TIME1) +
                     AccelerationAmount(amount = 10,
                                        unit = DISTANCE2 to TIME2))
    }

    @Test
    fun minusSameAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 0,
                                          unit = DISTANCE1 to TIME1),
            actual = AccelerationAmount(amount = 1,
                                        unit = DISTANCE1 to TIME1) -
                     AccelerationAmount(amount = 1,
                                        unit = DISTANCE1 to TIME1))
    }

    @Test
    fun minusOtherAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 0,
                                          unit = DISTANCE1 to TIME1),
            actual = AccelerationAmount(amount = 1,
                                        unit = DISTANCE1 to TIME1) -
                     AccelerationAmount(amount = 10,
                                        unit = DISTANCE2 to TIME2))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1) /
                     AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1) /
                     AccelerationAmount(amount = 20,
                                        unit = DISTANCE2 to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 * 2,
                                   unit = DISTANCE1 to TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 * 2,
                                   unit = DISTANCE1 to TIME2),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE2 to TIME2))
    }

    @Test
    fun timesMass()
    {
        assertEquals(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = DISTANCE1 to TIME1) *
                     MassAmount(amount = 2,
                                unit = MASS1))
    }
}