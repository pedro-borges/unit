package org.pcb.unit.amount

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.test.LENGTH1
import org.pcb.unit.amount.test.LENGTH2
import org.pcb.unit.amount.test.TIME1
import org.pcb.unit.amount.test.TIME2

class PaceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = PaceAmount(2 * 2, TIME1, LENGTH1),
            actual = PaceAmount(2, TIME1, LENGTH1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PaceAmount(2 / 2, TIME1, LENGTH1),
            actual = PaceAmount(2, TIME1, LENGTH1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSamePace()
    {
        assertEquals(
            expected = PaceAmount(0 + 1, TIME1, LENGTH1),
            actual = PaceAmount(0, TIME1, LENGTH1) +
                     PaceAmount(1, TIME1, LENGTH1))
    }

    @Test
    fun plusOtherPace()
    {
        assertEquals(
            expected = PaceAmount(0 + 1, TIME1, LENGTH1),
            actual = PaceAmount(0, TIME1, LENGTH1) +
                     PaceAmount(1, TIME2, LENGTH2))
    }

    @Test
    fun minusSamePace()
    {
        assertEquals(
            expected = PaceAmount(1 - 1, TIME1, LENGTH1),
            actual = PaceAmount(1, TIME1, LENGTH1) -
                     PaceAmount(1, TIME1, LENGTH1))
    }

    @Test
    fun minusOtherPace()
    {
        assertEquals(
            expected = PaceAmount(1 - 1, TIME1, LENGTH1),
            actual = PaceAmount(1, TIME1, LENGTH1) -
                     PaceAmount(1, TIME2, LENGTH2))
    }

    @Test
    fun divSamePace()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = PaceAmount(2, TIME1, LENGTH1) /
                     PaceAmount(2, TIME1, LENGTH1))
    }

    @Test
    fun divOtherPace()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = PaceAmount(2, TIME1, LENGTH1) /
                     PaceAmount(2, TIME2, LENGTH2))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = PaceAmount(2, TIME1, LENGTH1) *
                     SpeedAmount(2, LENGTH1, TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = PaceAmount(2, TIME1, LENGTH1) *
                     SpeedAmount(2, LENGTH2, TIME2))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = TimeAmount(2 * 2, TIME1),
            actual = PaceAmount(2, TIME1, LENGTH1) *
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = TimeAmount(2 * 2, TIME1),
            actual = PaceAmount(2, TIME1, LENGTH1) *
                     LengthAmount(1, LENGTH2))
    }
}