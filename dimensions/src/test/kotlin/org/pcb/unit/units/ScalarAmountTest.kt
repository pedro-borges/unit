package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.TIME1

class ScalarAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 * 2),
            actual = NaturalAmount(amount = 2) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = NaturalAmount(amount = 2) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameScalar()
    {
        assertEquals(
            expected = NaturalAmount(amount = 0 + 1),
            actual = NaturalAmount(amount = 0) +
                     NaturalAmount(amount = 1))
    }

    @Test
    fun plusOtherScalar()
    {
        assertEquals(
            expected = NaturalAmount(amount = 0 + 1),
            actual = NaturalAmount(amount = 0) +
                     PercentAmount(amount = 100))
    }

    @Test
    fun minusSameScalar()
    {
        assertEquals(
            expected = NaturalAmount(amount = 1 - 1),
            actual = NaturalAmount(amount = 1) -
                     NaturalAmount(amount = 1))
    }

    @Test
    fun minusOtherScalar()
    {
        assertEquals(
            expected = NaturalAmount(amount = 1 - 1),
            actual = NaturalAmount(amount = 1) -
                     PercentAmount(amount = 100))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       minor = TIME1),
            actual = NaturalAmount(amount = 2) /
                     TimeAmount(amount = 2,
                                major = TIME1))
    }

    @Test
    fun divFrequency()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 / 2,
                                  major = TIME1),
            actual = NaturalAmount(amount = 2) /
                     FrequencyAmount(amount = 2,
                                     minor = TIME1))
    }
}