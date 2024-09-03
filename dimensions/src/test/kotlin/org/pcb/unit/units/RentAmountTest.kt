package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class RentAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = RentAmount(2 * 2, CURRENCY1, TIME1),
            actual = RentAmount(2, CURRENCY1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = RentAmount(2 / 2, CURRENCY1, TIME1),
            actual = RentAmount(2, CURRENCY1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameRent()
    {
        assertEquals(
            expected = RentAmount(0 + 1, CURRENCY1, TIME1),
            actual = RentAmount(0, CURRENCY1, TIME1) +
                     RentAmount(1, CURRENCY1, TIME1))
    }

    @Test
    fun plusOtherRent()
    {
        assertEquals(
            expected = RentAmount(0 + 1, CURRENCY1, TIME1),
            actual = RentAmount(0, CURRENCY1, TIME1) +
                     RentAmount(2, CURRENCY1, TIME2))
    }

    @Test
    fun minusSameRent()
    {
        assertEquals(
            expected = RentAmount(1 - 1, CURRENCY1, TIME1),
            actual = RentAmount(1, CURRENCY1, TIME1) -
                     RentAmount(1, CURRENCY1, TIME1))
    }

    @Test
    fun minusOtherRent()
    {
        assertEquals(
            expected = RentAmount(1 - 1, CURRENCY1, TIME1),
            actual = RentAmount(1, CURRENCY1, TIME1) -
                     RentAmount(2, CURRENCY1, TIME2))
    }

    @Test
    fun divSameRent()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = RentAmount(2, CURRENCY1, TIME1) /
                     RentAmount(2, CURRENCY1, TIME1))
    }

    @Test
    fun divOtherRent()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = RentAmount(2, CURRENCY1, TIME1) /
                     RentAmount(2 * 2, CURRENCY1, TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(2 * 2, CURRENCY1),
            actual = RentAmount(2, CURRENCY1, TIME1) *
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = CurrencyAmount(2 * 2, CURRENCY1),
            actual = RentAmount(2, CURRENCY1, TIME1) *
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(2 / 2, TIME1),
            actual = RentAmount(2, CURRENCY1, TIME1) /
                     CurrencyAmount(2, CURRENCY1))
    }
}