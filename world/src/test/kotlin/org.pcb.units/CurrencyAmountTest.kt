package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.CurrencyAmount
import org.pcb.units.amount.DebtAmount
import org.pcb.units.amount.RentAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.finance.Gbp.GBP
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Second.SECOND

class CurrencyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = CurrencyAmount(4, GBP),
            actual = CurrencyAmount(2, GBP) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = CurrencyAmount(1, GBP),
            actual = CurrencyAmount(2, GBP) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(1, GBP),
            actual = CurrencyAmount(0, GBP) + CurrencyAmount(1, GBP))
    }

    @Test
    fun minusSameCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(0, GBP),
            actual = CurrencyAmount(1, GBP) - CurrencyAmount(1, GBP))
    }

    @Test
    fun divSameCurrency()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = CurrencyAmount(2, GBP) / CurrencyAmount(2, GBP))
    }

    @Test
    fun timesTime()
    {
        assertEquals(
            expected = DebtAmount(4, GBP, SECOND),
            actual = CurrencyAmount(2, GBP) * TimeAmount(2, SECOND))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = RentAmount(1, GBP, SECOND),
            actual = CurrencyAmount(2, GBP) / TimeAmount(2, SECOND))
    }
}