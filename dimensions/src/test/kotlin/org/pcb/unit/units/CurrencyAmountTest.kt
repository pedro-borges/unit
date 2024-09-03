package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.CURRENCY2

class CurrencyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = CurrencyAmount(2 * 2, CURRENCY1),
            actual = CurrencyAmount(2, CURRENCY1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = CurrencyAmount(2, CURRENCY1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(0 + 1, CURRENCY1),
            actual = CurrencyAmount(0, CURRENCY1) +
                     CurrencyAmount(1, CURRENCY1))
    }

    @Test
    fun plusOtherCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(0 + 2, CURRENCY1),
            actual = CurrencyAmount(0, CURRENCY1) +
                     CurrencyAmount(1, CURRENCY2))
    }

    @Test
    fun minusSameCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(1 - 1, CURRENCY1),
            actual = CurrencyAmount(1, CURRENCY1) -
                     CurrencyAmount(1, CURRENCY1))
    }

    @Test
    fun minusOtherCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(2 - 2, CURRENCY1),
            actual = CurrencyAmount(2, CURRENCY1) -
                     CurrencyAmount(1, CURRENCY2))
    }

    @Test
    fun divSameCurrency()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = CurrencyAmount(2, CURRENCY1) /
                     CurrencyAmount(2, CURRENCY1))
    }

    @Test
    fun divOtherCurrency()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = CurrencyAmount(2, CURRENCY1) /
                     CurrencyAmount(1, CURRENCY2))
    }

    @Test
    fun timesTime()
    {
        assertEquals<DebtAmount<out Unit<Currency>, SECOND>>(
            expected = DebtAmount(2 * 2, CURRENCY1 * SECOND),
            actual = CurrencyAmount(2, CURRENCY1) *
                     TimeAmount(2, SECOND))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = RentAmount(1, CURRENCY1 to SECOND),
            actual = CurrencyAmount(2, CURRENCY1) /
                     TimeAmount(2, SECOND))
    }
}