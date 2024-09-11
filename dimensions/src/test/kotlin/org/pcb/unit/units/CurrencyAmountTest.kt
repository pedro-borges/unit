package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.CURRENCY2
import org.pcb.unit.units.test.TIME1

class CurrencyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 * 2,
                                      unit = CURRENCY1),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = CurrencyAmount(amount = 0,
                                    unit = CURRENCY1) +
                     CurrencyAmount(amount = 1,
                                    unit = CURRENCY1))
    }

    @Test
    fun plusOtherCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY2),
            actual = CurrencyAmount(amount = 0,
                                    unit = CURRENCY2) +
                     CurrencyAmount(amount = 10,
                                    unit = CURRENCY1))
    }

    @Test
    fun minusSameCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 0,
                                      unit = CURRENCY1),
            actual = CurrencyAmount(amount = 1,
                                    unit = CURRENCY1) -
                     CurrencyAmount(amount = 1,
                                    unit = CURRENCY1))
    }

    @Test
    fun minusOtherCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 0,
                                      unit = CURRENCY2),
            actual = CurrencyAmount(amount = 1,
                                    unit = CURRENCY2) -
                     CurrencyAmount(amount = 10,
                                    unit = CURRENCY1))
    }

    @Test
    fun divSameCurrency()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) /
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }

    @Test
    fun divOtherCurrency()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY2) /
                     CurrencyAmount(amount = 20,
                                    unit = CURRENCY1))
    }

    @Test
    fun timesTime()
    {
        assertEquals(
            expected = DebtAmount(amount = 2 * 2,
                                  unit = U2(CURRENCY1, TIME1)),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 to TIME1),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }
}