package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.NaturalAmount.ONE
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
        assertEquals<RentAmount<out Unit<Currency>, TIME1>>(
            expected = RentAmount(amount = 2 * 2,
                                  major = CURRENCY1,
                                  minor = TIME1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<RentAmount<out Unit<Currency>, TIME1>>(
            expected = RentAmount(amount = 2 / 2,
                                  major = CURRENCY1,
                                  minor = TIME1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameRent()
    {
        assertEquals<RentAmount<out Unit<Currency>, TIME1>>(
            expected = RentAmount(amount = 0 + 1,
                                  major = CURRENCY1,
                                  minor = TIME1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 0,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) +
                     RentAmount(amount = 1,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1))
    }

    @Test
    fun plusOtherRent()
    {
        assertEquals<RentAmount<out Unit<Currency>, TIME1>>(
            expected = RentAmount(amount = 0 + 1,
                                  major = CURRENCY1,
                                  minor = TIME1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 0,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) +
                     RentAmount(amount = 1,
                                major = CURRENCY2,
                                minor = TIME2,
                                unit = CURRENCY2 / TIME2))
    }

    @Test
    fun minusSameRent()
    {
        assertEquals<RentAmount<out Unit<Currency>, TIME1>>(
            expected = RentAmount(amount = 1 - 1,
                                  major = CURRENCY1,
                                  minor = TIME1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 1,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) -
                     RentAmount(amount = 1,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1))
    }

    @Test
    fun minusOtherRent()
    {
        assertEquals<RentAmount<out Unit<Currency>, TIME1>>(
            expected = RentAmount(amount = 1 - 1,
                                  major = CURRENCY1,
                                  minor = TIME1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 1,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) -
                     RentAmount(amount = 1,
                                major = CURRENCY2,
                                minor = TIME2,
                                unit = CURRENCY2 / TIME2))
    }

    @Test
    fun divSameRent()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) /
                     RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1))
    }

    @Test
    fun divOtherRent()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) /
                     RentAmount(amount = 2,
                                major = CURRENCY2,
                                minor = TIME2,
                                unit = CURRENCY2 / TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 * 2,
                                      unit = CURRENCY1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 * 2,
                                      unit = CURRENCY1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) *
                     TimeAmount(amount = 1,
                                unit = TIME2))
    }

    @Test
    fun divSameCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       unit = ONE / TIME1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) /
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }

    @Test
    fun divOtherCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       unit = ONE / TIME1),
            actual = RentAmount(amount = 2,
                                major = CURRENCY1,
                                minor = TIME1,
                                unit = CURRENCY1 / TIME1) /
                     CurrencyAmount(amount = 1,
                                    unit = CURRENCY2))
    }
}