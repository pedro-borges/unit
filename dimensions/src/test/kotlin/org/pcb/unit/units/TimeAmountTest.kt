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
            expected = TimeAmount(amount = 2 * 2,
                                  major = TIME1),
            actual = TimeAmount(amount = 2,
                                major = TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 / 2,
                                  major = TIME1),
            actual = TimeAmount(amount = 2,
                                major = TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 0 + 1,
                                  major = TIME1),
            actual = TimeAmount(amount = 0,
                                major = TIME1) +
                     TimeAmount(amount = 1,
                                major = TIME1))
    }

    @Test
    fun plusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 0 + 2,
                                  major = TIME1),
            actual = TimeAmount(amount = 0,
                                major = TIME1) +
                     TimeAmount(amount = 1,
                                major = TIME2))
    }

    @Test
    fun minusSameTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 1 - 1,
                                  major = TIME1),
            actual = TimeAmount(amount = 1,
                                major = TIME1) -
                     TimeAmount(amount = 1,
                                major = TIME1))
    }

    @Test
    fun minusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 - 2,
                                  major = TIME1),
            actual = TimeAmount(amount = 2,
                                major = TIME1) -
                     TimeAmount(amount = 1,
                                major = TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = NaturalAmount(amount = 1),
            actual = TimeAmount(amount = 2,
                                major = TIME1) /
                     TimeAmount(amount = 2,
                                major = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = TimeAmount(amount = 2,
                                major = TIME1) /
                     TimeAmount(amount = 1,
                                major = TIME2))
    }

    @Test
    fun divSpace()
    {
        assertEquals(
            expected = PaceAmount(amount = 2 / 2,
                                  major = TIME1,
                                  minor = LENGTH1),
            actual = TimeAmount(amount = 2,
                                major = TIME1) /
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }
}
