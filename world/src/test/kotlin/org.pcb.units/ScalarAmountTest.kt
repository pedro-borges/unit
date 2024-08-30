package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Second.SECOND

class NaturalAmountTest
{
    @Test
    fun plusSameScalar()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = NaturalAmount(0) + NaturalAmount(1))
    }

    @Test
    fun plusOtherScalar()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = NaturalAmount(0) + PercentAmount(100))
    }

    @Test
    fun minusSameScalar()
    {
        assertEquals(
            expected = NaturalAmount(0),
            actual = NaturalAmount(1) - NaturalAmount(1))
    }

    @Test
    fun minusOtherScalar()
    {
        assertEquals(
            expected = NaturalAmount(0),
            actual = NaturalAmount(1) - PercentAmount(100))
    }

    @Test
    fun divSameScalar()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = NaturalAmount(2) / NaturalAmount(2))
    }

    @Test
    fun divOtherScalar()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = NaturalAmount(2) / PercentAmount(2 * 100))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = NaturalAmount(2) / TimeAmount(2, SECOND))
    }

    @Test
    fun divFrequency()
    {
        assertEquals(
            expected = TimeAmount(1, SECOND),
            actual = NaturalAmount(2) / FrequencyAmount(2, SECOND))
    }
}