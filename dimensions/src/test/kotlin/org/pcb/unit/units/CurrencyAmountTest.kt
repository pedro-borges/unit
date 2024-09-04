package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.CURRENCY2

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
            expected = CurrencyAmount(amount = 2 / 2,
                                      unit = CURRENCY1),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 0 + 1,
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
            expected = CurrencyAmount(amount = 0 + 2,
                                      unit = CURRENCY1),
            actual = CurrencyAmount(amount = 0,
                                    unit = CURRENCY1) +
                     CurrencyAmount(amount = 1,
                                    unit = CURRENCY2))
    }

    @Test
    fun minusSameCurrency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1 - 1,
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
            expected = CurrencyAmount(amount = 2 - 2,
                                      unit = CURRENCY1),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) -
                     CurrencyAmount(amount = 1,
                                    unit = CURRENCY2))
    }

    @Test
    fun divSameCurrency()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) /
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }

    @Test
    fun divOtherCurrency()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) /
                     CurrencyAmount(amount = 1,
                                    unit = CURRENCY2))
    }

    @Test
    fun timesTime()
    {
        assertEquals<DebtAmount<out Unit<Currency>, SECOND>>(
            expected = DebtAmount(amount = 2 * 2,
                                  unit = CURRENCY1 * SECOND),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) *
                     TimeAmount(amount = 2,
                                unit = SECOND))
    }

    @Test
    fun divTime()
    {
        assertEquals<RentAmount<out Unit<Currency>, SECOND>>(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 / SECOND),
            actual = CurrencyAmount(amount = 2,
                                    unit = CURRENCY1) /
                     TimeAmount(amount = 2,
                                unit = SECOND))
    }
}