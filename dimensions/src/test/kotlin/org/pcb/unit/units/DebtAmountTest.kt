package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.CurrencyAmount
import org.pcb.unit.units.DebtAmount
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.CURRENCY2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class DebtAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = DebtAmount(2 * 2, CURRENCY1, TIME1),
            actual = DebtAmount(2, CURRENCY1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = DebtAmount(2 / 2, CURRENCY1, TIME1),
            actual = DebtAmount(2, CURRENCY1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameDebt()
    {
        assertEquals(
            expected = DebtAmount(0 + 1, CURRENCY1, TIME1),
            actual = DebtAmount(0, CURRENCY1, TIME1) +
                     DebtAmount(1, CURRENCY1, TIME1))
    }

    @Test
    fun plusOtherDebt()
    {
        assertEquals(
            expected = DebtAmount(0 + (2 * 2), CURRENCY1, TIME1),
            actual = DebtAmount(0, CURRENCY1, TIME1) +
                     DebtAmount(1, CURRENCY2, TIME2))
    }

    @Test
    fun minusSameDebt()
    {
        assertEquals(
            expected = DebtAmount(1 - 1, CURRENCY1, TIME1),
            actual = DebtAmount(1, CURRENCY1, TIME1) -
                     DebtAmount(1, CURRENCY1, TIME1))
    }

    @Test
    fun minusOtherDebt()
    {
        assertEquals(
            expected = DebtAmount(4 - (2 * 2), CURRENCY1, TIME1),
            actual = DebtAmount(4, CURRENCY1, TIME1) -
                     DebtAmount(1, CURRENCY2, TIME2))
    }

    @Test
    fun divSameDebt()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = DebtAmount(2, CURRENCY1, TIME1) /
                     DebtAmount(2, CURRENCY1, TIME1))
    }

    @Test
    fun divOtherDebt()
    {
        assertEquals(
            expected = NaturalAmount(4 / (2 * 2)),
            actual = DebtAmount(4, CURRENCY1, TIME1) /
                     DebtAmount(1, CURRENCY2, TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = DebtAmount(2, CURRENCY1, TIME1) /
                     TimeAmount(2, TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = DebtAmount(2, CURRENCY1, TIME1) /
                     TimeAmount(1, TIME2))
    }

    @Test
    fun timesSameFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = DebtAmount(2, CURRENCY1, TIME1) *
                     FrequencyAmount(2, TIME1))
    }

    @Test
    fun timesOtherFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = DebtAmount(2, CURRENCY1, TIME1) *
                     FrequencyAmount(1, TIME2))
    }
}