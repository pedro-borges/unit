package org.pcb.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.CurrencyAmount
import org.pcb.units.amount.DebtAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.finance.Gbp.GBP
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class DebtAmountTest
{
    @Test
    fun plusSameDebt()
    {
        assertEquals(
            expected = DebtAmount(1, GBP, SECOND),
            actual = DebtAmount(0, GBP, SECOND) + DebtAmount(1, GBP, SECOND))
    }

    @Test
    fun plusOtherDebt()
    {
        assertEquals(
            expected = DebtAmount(1, GBP, MINUTE),
            actual = DebtAmount(0, GBP, MINUTE) + DebtAmount(60, GBP, SECOND))
    }

    @Test
    fun minusSameDebt()
    {
        assertEquals(
            expected = DebtAmount(0, GBP, SECOND),
            actual = DebtAmount(1, GBP, SECOND) - DebtAmount(1, GBP, SECOND))
    }

    @Test
    fun minusOtherDebt()
    {
        assertEquals(
            expected = DebtAmount(0, GBP, MINUTE),
            actual = DebtAmount(1, GBP, MINUTE) - DebtAmount(60, GBP, SECOND))
    }

    @Test
    fun divSameDebt()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = DebtAmount(2, GBP, SECOND) / DebtAmount(2, GBP, SECOND))
    }

    @Test
    fun divOtherDebt()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = DebtAmount(2, GBP, MINUTE) / DebtAmount(2 * 60, GBP, SECOND))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(1, GBP),
            actual = DebtAmount(2, GBP, SECOND) / TimeAmount(2, SECOND))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = CurrencyAmount(1, GBP),
            actual = DebtAmount(2, GBP, MINUTE) / TimeAmount(2 * 60, SECOND))
    }

    @Test
    fun timesSameFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(1, GBP),
            actual = DebtAmount(2, GBP, SECOND) * FrequencyAmount(2, SECOND))
    }

    @Test
    fun timesOtherFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(1, GBP),
            actual = DebtAmount(2, GBP, MINUTE) * FrequencyAmount(2 * 60, SECOND))
    }
}