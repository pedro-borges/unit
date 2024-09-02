package org.pcb.unit

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.amount.AreaAmount
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.SpeedAmount
import org.pcb.unit.amount.TimeAmount
import org.pcb.unit.amount.VolumeAmount
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.test.LENGTH1
import org.pcb.unit.amount.test.LENGTH2
import org.pcb.unit.amount.test.TIME1

class LengthAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LengthAmount(2 * 2, LENGTH1),
            actual = LengthAmount(2, LENGTH1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LengthAmount(2 / 2, LENGTH1),
            actual = LengthAmount(2, LENGTH1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameLength()
    {
        assertEquals(
            expected = LengthAmount(0 + 1, LENGTH1),
            actual = LengthAmount(0, LENGTH1) +
                     LengthAmount(1, LENGTH1))
    }

    @Test
    fun plusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(0 + 2, LENGTH1),
            actual = LengthAmount(0, LENGTH1) +
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun minusSameLength()
    {
        assertEquals(
            expected = LengthAmount(2 - 2, LENGTH1),
            actual = LengthAmount(2, LENGTH1) -
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun minusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(2 - 2, LENGTH1),
            actual = LengthAmount(2, LENGTH1) -
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = LengthAmount(2, LENGTH1) /
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = LengthAmount(2, LENGTH1) /
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1, TIME1),
            actual = LengthAmount(2, LENGTH1) /
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals(
            expected = AreaAmount(2 * 2, LENGTH1, LENGTH1),
            actual = LengthAmount(2, LENGTH1) *
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals(
            expected = AreaAmount(2 * 2, LENGTH1, LENGTH1),
            actual = LengthAmount(2, LENGTH1) *
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun timesSameArea()
    {
        assertEquals(
            expected = VolumeAmount(2 * 2, LENGTH1, LENGTH1, LENGTH1),
            actual = LengthAmount(2, LENGTH1) *
                     AreaAmount(2, LENGTH1, LENGTH1))
    }

    @Test
    fun timesOtherArea()
    {
        assertEquals(
            expected = VolumeAmount(2 * 2 * 2, LENGTH1, LENGTH1, LENGTH1),
            actual = LengthAmount(2, LENGTH1) *
                     AreaAmount(1, LENGTH2, LENGTH2))
    }
}
