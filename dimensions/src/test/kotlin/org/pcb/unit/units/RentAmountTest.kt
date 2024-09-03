package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.CURRENCY2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class RentAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = RentAmount(amount = 2 * 2,
                                  major = CURRENCY1,
                                  minor = TIME1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = RentAmount(amount = 2 / 2,
                                  major = CURRENCY1,
                                  minor = TIME1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameRent()
    {
        assertEquals(
            expected = RentAmount(amount = 0 + 1,
                                  major = CURRENCY1,
                                  minor = TIME1),
            actual = RentAmount(amount = 0,
                                major = CURRENCY1,
                                minor = TIME1) +
                     RentAmount(amount = 1,
                                major = CURRENCY1,
                                minor = TIME1))
    }

    @Test
    fun plusOtherRent()
    {
        assertEquals(
            expected = RentAmount(0 + 1, CURRENCY1, TIME1),
            actual = RentAmount(amount = 0,
                                major = CURRENCY1,
                                minor = TIME1) +
                     RentAmount(amount = 1,
                                major = CURRENCY2,
                                minor = TIME2))
    }

    @Test
    fun minusSameRent()
    {
        assertEquals(
            expected = RentAmount(amount = 1 - 1,
                                  major = CURRENCY1, minor = TIME1),
            actual = RentAmount(amount = 1,
                                major = CURRENCY1, minor = TIME1) -
                     RentAmount(amount = 1,
                                major = CURRENCY1,
                                minor = TIME1))
    }

    @Test
    fun minusOtherRent()
    {
        assertEquals(
            expected = RentAmount(amount = 1 - 1,
                                  major = CURRENCY1, minor = TIME1),
            actual = RentAmount(amount = 1,
                                major = CURRENCY1, minor = TIME1) -
                     RentAmount(amount = 1,
                                major = CURRENCY2,
                                minor = TIME2))
    }

    @Test
    fun divSameRent()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1, minor = TIME1) /
                     RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1))
    }

    @Test
    fun divOtherRent()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1) /
                     RentAmount(amount = 2,
                                major = CURRENCY2,
                                minor = TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 * 2,
                                      major = CURRENCY1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1) *
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 * 2,
                                      major = CURRENCY1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1) *
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       minor = TIME1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1) /
                     CurrencyAmount(amount = 2,
                                    major = CURRENCY1))
    }

    @Test
    fun divOtherCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       minor = TIME1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1) /
                     CurrencyAmount(amount = 1,
                                    major = CURRENCY2))
    }
}