package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.Fraction
import org.pcb.unit.div
import org.pcb.unit.units.scalar.Numeric.ONE
import org.pcb.unit.units.scalar.Percent
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
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 / TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 / TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameRent()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 0,
                                unit = CURRENCY1 / TIME1) +
                     RentAmount(amount = 1,
                                unit = CURRENCY1 / TIME1))
    }

    @Test
    fun plusOtherRent()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 0,
                                unit = CURRENCY1 / TIME1) +
                     RentAmount(amount = 1,
                                unit = CURRENCY2 / TIME2))
    }

    @Test
    fun minusSameRent()
    {
        assertEquals(
            expected = RentAmount(amount = 0,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 1,
                                unit = CURRENCY1 / TIME1) -
                     RentAmount(amount = 1,
                                unit = CURRENCY1 / TIME1))
    }

    @Test
    fun minusOtherRent()
    {
        assertEquals(
            expected = RentAmount(amount = 0,
                                  unit = CURRENCY1 / TIME1),
            actual = RentAmount(amount = 1,
                                unit = CURRENCY1 / TIME1) -
                     RentAmount(amount = 1,
                                unit = CURRENCY2 / TIME2))
    }

    @Test
    fun divSameRent()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 / TIME1) /
                     RentAmount(amount = 2,
                                unit = CURRENCY1 / TIME1))
    }

    @Test
    fun divOtherRent()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 / TIME1) /
                     RentAmount(amount = 2,
                                unit = CURRENCY2 / TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 * 2,
                                      unit = CURRENCY1),
            actual = RentAmount(amount = 2,
                                unit = Fraction(CURRENCY1, TIME1)) *
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
                                unit = Fraction(CURRENCY1, TIME2)) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = 1 / TIME1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 / TIME1) /
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }

    @Test
    fun divOtherCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = Fraction(ONE, TIME1)),
            actual = RentAmount(amount = 2,
                                unit = Fraction(CURRENCY2, TIME1)) /
                     CurrencyAmount(amount = 20,
                                    unit = CURRENCY1))
    }
}