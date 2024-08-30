package org.pcb.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.CurrencyAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.RentAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.finance.Gbp.GBP
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Day.DAY
import org.pcb.units.amount.time.Hour.HOUR

class RentAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = RentAmount(4, GBP, SECOND),
            actual = RentAmount(2, GBP, SECOND) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = RentAmount(1, GBP, SECOND),
            actual = RentAmount(2, GBP, SECOND) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameRent()
    {
        assertEquals(
            expected = RentAmount(1, GBP, DAY),
            actual = RentAmount(0, GBP, DAY) + RentAmount(1, GBP, DAY))
    }

    @Test
    fun plusOtherRent()
    {
        assertEquals(
            expected = RentAmount(1, GBP, DAY),
            actual = RentAmount(0, GBP, DAY) + RentAmount(24, GBP, HOUR))
    }

    @Test
    fun minusSameRent()
    {
        assertEquals(
            expected = RentAmount(0, GBP, DAY),
            actual = RentAmount(1, GBP, DAY) - RentAmount(1, GBP, DAY))
    }

    @Test
    fun minusOtherRent()
    {
        assertEquals(
            expected = RentAmount(0, GBP, DAY),
            actual = RentAmount(1, GBP, DAY) - RentAmount(24, GBP, HOUR))
    }

    @Test
    fun divSameRent()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = RentAmount(2, GBP, DAY) / RentAmount(2, GBP, DAY))
    }

    @Test
    fun divOtherRent()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = RentAmount(2, GBP, DAY) / RentAmount(48, GBP, HOUR))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(4, GBP),
            actual = RentAmount(2, GBP, DAY) * TimeAmount(2, DAY))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = CurrencyAmount(4, GBP),
            actual = RentAmount(2, GBP, DAY) * TimeAmount(48, HOUR))
    }

    @Test
    fun divCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(1, DAY),
            actual = RentAmount(2, GBP, DAY) / CurrencyAmount(2, GBP))
    }
}