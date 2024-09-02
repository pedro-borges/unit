package org.pcb.unit.amount

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.test.CURRENCY1
import org.pcb.unit.amount.test.LENGTH1
import org.pcb.unit.amount.test.TIME1
import org.pcb.unit.amount.test.TIME2

class FrequencyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = FrequencyAmount(2 * 2, TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = FrequencyAmount(2 / 2, TIME1),
            actual = FrequencyAmount(2, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(0 + 1, TIME1),
            actual = FrequencyAmount(0, TIME1) +
                     FrequencyAmount(1, TIME1))
    }

    @Test
    fun plusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(0 + 1, TIME1),
            actual = FrequencyAmount(0, TIME1) +
                     FrequencyAmount(2, TIME2))
    }

    @Test
    fun minusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(1 - 1, TIME1),
            actual = FrequencyAmount(1, TIME1) -
                     FrequencyAmount(1, TIME1))
    }

    @Test
    fun minusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(1 - 1, TIME1),
            actual = FrequencyAmount(1, TIME1) -
                     FrequencyAmount(2, TIME2))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = FrequencyAmount(2, TIME1) /
                     FrequencyAmount(2, TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = FrequencyAmount(1, TIME1) /
                     FrequencyAmount(2, TIME2))
    }

    @Test
    fun timesSpace()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1, TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(2 / 2, LENGTH1, TIME1, TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     SpeedAmount(2, LENGTH1, TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(2 / 2, LENGTH1, TIME1, TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     SpeedAmount(2 * 2, LENGTH1, TIME2))
    }

    @Test
    fun timesSameDebt()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = FrequencyAmount(2, TIME1) *
                     DebtAmount(2, CURRENCY1, TIME1))
    }

    @Test
    fun timesOtherDebt()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = FrequencyAmount(2, TIME1) *
                     DebtAmount(1, CURRENCY1, TIME2))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals(
            expected = RentAmount(2 / 2, CURRENCY1, TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     CurrencyAmount(2, CURRENCY1))
    }
}