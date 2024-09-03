package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount

class ScalarAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = NaturalAmount(4),
            actual = NaturalAmount(2) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = NaturalAmount(2) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameScalar()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = NaturalAmount(0) +
                     NaturalAmount(1))
    }

    @Test
    fun plusOtherScalar()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = NaturalAmount(0) +
                     PercentAmount(100))
    }

    @Test
    fun minusSameScalar()
    {
        assertEquals(
            expected = NaturalAmount(0),
            actual = NaturalAmount(1) -
                     NaturalAmount(1))
    }

    @Test
    fun minusOtherScalar()
    {
        assertEquals(
            expected = NaturalAmount(0),
            actual = NaturalAmount(1) -
                     PercentAmount(100))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = NaturalAmount(2) /
                     TimeAmount(2, SECOND))
    }

    @Test
    fun divFrequency()
    {
        assertEquals(
            expected = TimeAmount(1, SECOND),
            actual = NaturalAmount(2) /
                     FrequencyAmount(2, SECOND))
    }
}