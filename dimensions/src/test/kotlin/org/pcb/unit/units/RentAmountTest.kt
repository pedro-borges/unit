package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Numeric.NUMERIC
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
                                  unit = CURRENCY1 to TIME1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 to TIME1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameRent()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 to TIME1),
            actual = RentAmount(amount = 0,
                                unit = CURRENCY1 to TIME1) +
                     RentAmount(amount = 1,
                                unit = CURRENCY1 to TIME1))
    }

    @Test
    fun plusOtherRent()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY2 to TIME1),
            actual = RentAmount(amount = 0,
                                unit = CURRENCY2 to TIME1) +
                     RentAmount(amount = 100,
                                unit = CURRENCY1 to TIME2))
    }

    @Test
    fun minusSameRent()
    {
        assertEquals(
            expected = RentAmount(amount = 0,
                                  unit = CURRENCY1 to TIME1),
            actual = RentAmount(amount = 1,
                                unit = CURRENCY1 to TIME1) -
                     RentAmount(amount = 1,
                                unit = CURRENCY1 to TIME1))
    }

    @Test
    fun minusOtherRent()
    {
        assertEquals(
            expected = RentAmount(amount = 0,
                                  unit = CURRENCY2 to TIME1),
            actual = RentAmount(amount = 1,
                                unit = CURRENCY2 to TIME1) -
                     RentAmount(amount = 100,
                                unit = CURRENCY1 to TIME2))
    }

    @Test
    fun divSameRent()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 to TIME1) /
                     RentAmount(amount = 2,
                                unit = CURRENCY1 to TIME1))
    }

    @Test
    fun divOtherRent()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY2 to TIME1) /
                     RentAmount(amount = 200,
                                unit = CURRENCY1 to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 * 2,
                                      unit = CURRENCY1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 to TIME1) *
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
                                unit = CURRENCY1 to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY1 to TIME1) /
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }

    @Test
    fun divOtherCurrency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = RentAmount(amount = 2,
                                unit = CURRENCY2 to TIME1) /
                     CurrencyAmount(amount = 20,
                                    unit = CURRENCY1))
    }
}