package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.Currency
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
        assertEquals<DebtAmount<out Unit<Currency>, TIME1>>(
            expected = DebtAmount(amount = 2 * 2,
                                  major = CURRENCY1 * TIME1),
            actual = DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<DebtAmount<out Unit<Currency>, TIME1>>(
            expected = DebtAmount(amount = 2 / 2,
                                  major = CURRENCY1 * TIME1),
            actual = DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameDebt()
    {
        assertEquals<DebtAmount<out Unit<Currency>, TIME1>>(
            expected = DebtAmount(amount = 0 + 1,
                                  major = CURRENCY1 * TIME1),
            actual = DebtAmount(amount = 0,
                                major = CURRENCY1 * TIME1) +
                     DebtAmount(amount = 1,
                                major = CURRENCY1 * TIME1))
    }

    @Test
    fun plusOtherDebt()
    {
        assertEquals<DebtAmount<out Unit<Currency>, TIME1>>(
            expected = DebtAmount(amount = 0 + (2 * 2),
                                  major = CURRENCY1 * TIME1),
            actual = DebtAmount(amount = 0,
                                major = CURRENCY1 * TIME1) +
                     DebtAmount(amount = 1,
                                major = CURRENCY2 * TIME2))
    }

    @Test
    fun minusSameDebt()
    {
        assertEquals<DebtAmount<out Unit<Currency>, TIME1>>(
            expected = DebtAmount(amount = 1 - 1,
                                  major = CURRENCY1 * TIME1),
            actual = DebtAmount(amount = 1,
                                major = CURRENCY1 * TIME1) -
                     DebtAmount(amount = 1,
                                major = CURRENCY1 * TIME1))
    }

    @Test
    fun minusOtherDebt()
    {
        assertEquals<DebtAmount<out Unit<Currency>, TIME1>>(
            expected = DebtAmount(amount = 4 - (2 * 2),
                                  major = CURRENCY1 * TIME1),
            actual = DebtAmount(amount = 4,
                                major = CURRENCY1 * TIME1) -
                     DebtAmount(amount = 1,
                                major = CURRENCY2 * TIME2))
    }

    @Test
    fun divSameDebt()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1) /
                     DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1))
    }

    @Test
    fun divOtherDebt()
    {
        assertEquals(
            expected = NaturalAmount(amount = 4 / (2 * 2)),
            actual = DebtAmount(amount = 4,
                                major = CURRENCY1 * TIME1) /
                     DebtAmount(amount = 1,
                                major = CURRENCY2 * TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 / 2,
                                      major = CURRENCY1),
            actual = DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1) /
                     TimeAmount(amount = 2,
                                major = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 / 2,
                                      major = CURRENCY1),
            actual = DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1) /
                     TimeAmount(amount = 1,
                                major = TIME2))
    }

    @Test
    fun timesSameFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 / 2,
                                      major = CURRENCY1),
            actual = DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1) *
                     FrequencyAmount(amount = 2,
                                     minor = TIME1))
    }

    @Test
    fun timesOtherFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 / 2,
                                      major = CURRENCY1),
            actual = DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1) *
                     FrequencyAmount(amount = 1,
                                     minor = TIME2))
    }
}