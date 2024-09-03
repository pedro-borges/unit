package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class TimeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = TimeAmount(2 * 2, TIME1),
            actual = TimeAmount(2, TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = TimeAmount(2 / 2, TIME1),
            actual = TimeAmount(2, TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameTime()
    {
        assertEquals(
            expected = TimeAmount(0 + 1, TIME1),
            actual = TimeAmount(0, TIME1) +
                     TimeAmount(1, TIME1))
    }

    @Test
    fun plusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(0 + 2, TIME1),
            actual = TimeAmount(0, TIME1) +
                     TimeAmount(1, TIME2))
    }

    @Test
    fun minusSameTime()
    {
        assertEquals(
            expected = TimeAmount(1 - 1, TIME1),
            actual = TimeAmount(1, TIME1) -
                     TimeAmount(1, TIME1))
    }

    @Test
    fun minusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(2 - 2, TIME1),
            actual = TimeAmount(2, TIME1) -
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = NaturalAmount(amount = 1),
            actual = TimeAmount(2, TIME1) /
                     TimeAmount(2, TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = TimeAmount(2, TIME1) /
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSpace()
    {
        assertEquals(
            expected = PaceAmount(amount = 2 / 2,
                                  major = TIME1,
                                  minor = LENGTH1),
            actual = TimeAmount(2, TIME1) /
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }
}
