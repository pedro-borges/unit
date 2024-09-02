package org.pcb.unit.amount

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.test.LENGTH1
import org.pcb.unit.amount.test.LENGTH2
import org.pcb.unit.amount.test.MASS1
import org.pcb.unit.amount.test.TIME1
import org.pcb.unit.amount.test.TIME2
import org.pcb.unit.dimension.Time

class AccelerationAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(1, LENGTH1, TIME1, TIME1),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = AccelerationAmount(2 * 2, LENGTH1, TIME1, TIME1),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = AccelerationAmount(2 / 2, LENGTH1, TIME1, TIME1),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(0 + 1, LENGTH1, TIME1, TIME1),
            actual = AccelerationAmount(0, LENGTH1, TIME1, TIME1) +
                     AccelerationAmount(1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun plusOtherAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(0 + 1, LENGTH1, TIME1, TIME1),
            actual = AccelerationAmount(0, LENGTH1, TIME1, TIME1) +
                     AccelerationAmount(2 * 2, LENGTH1, TIME2, TIME2))
    }

    @Test
    fun minusSameAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(1 - 1, LENGTH1, TIME1, TIME1),
            actual = AccelerationAmount(1, LENGTH1, TIME1, TIME1) -
                     AccelerationAmount(1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun minusOtherAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(1 - 1, LENGTH1, TIME1, TIME1),
            actual = AccelerationAmount(1, LENGTH1, TIME1, TIME1) -
                     AccelerationAmount(2 * 2, LENGTH1, TIME2, TIME2))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) /
                     AccelerationAmount(2, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) /
                     AccelerationAmount(2 * (2 * 2), LENGTH1, TIME2, TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = SpeedAmount(2 * 2, LENGTH1, TIME1),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) *
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = SpeedAmount(2 * 2, LENGTH1, TIME1),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) *
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(2 / 2, TIME1),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) /
                     SpeedAmount(2, LENGTH1, TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(2 / 2, TIME1),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) /
                     SpeedAmount(2, LENGTH2, TIME2))
    }

    @Test
    fun timesMass()
    {
        assertEquals(
            expected = ForceAmount(2 * 2, MASS1, LENGTH1, TIME1, TIME1),
            actual = AccelerationAmount(2, LENGTH1, TIME1, TIME1) *
                     MassAmount(2, MASS1))
    }
}