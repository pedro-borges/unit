package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.CurrencyAmount
import org.pcb.unit.units.DebtAmount
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.Numeric.ONE
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.CURRENCY2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class DebtAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = DebtAmount(amount = 2 * 2,
                                  unit = T2(CURRENCY1, TIME1)),
            actual = DebtAmount(amount = 2,
                                unit = T2(CURRENCY1, TIME1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = DebtAmount(amount = 1,
                                  unit = T2(CURRENCY1, TIME1)),
            actual = DebtAmount(amount = 2,
                                unit = T2(CURRENCY1, TIME1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameDebt()
    {
        assertEquals(
            expected = DebtAmount(amount = 1,
                                  unit = T2(CURRENCY1, TIME1)),
            actual = DebtAmount(amount = 0,
                                unit = T2(CURRENCY1, TIME1)) +
                     DebtAmount(amount = 1,
                                unit = T2(CURRENCY1, TIME1)))
    }

    @Test
    fun plusOtherDebt()
    {
        assertEquals(
            expected = DebtAmount(amount = 1,
                                  unit = T2(CURRENCY2, TIME2)),
            actual = DebtAmount(amount = 0,
                                unit = T2(CURRENCY2, TIME2)) +
                     DebtAmount(amount = 100,
                                unit = T2(CURRENCY1, TIME1)))
    }

    @Test
    fun minusSameDebt()
    {
        assertEquals(
            expected = DebtAmount(amount = 0,
                                  unit = T2(CURRENCY1, TIME1)),
            actual = DebtAmount(amount = 1,
                                unit = T2(CURRENCY1, TIME1)) -
                     DebtAmount(amount = 1,
                                unit = T2(CURRENCY1, TIME1)))
    }

    @Test
    fun minusOtherDebt()
    {
        assertEquals(
            expected = DebtAmount(amount = 0,
                                  unit = T2(CURRENCY2, TIME2)),
            actual = DebtAmount(amount = 1,
                                unit = T2(CURRENCY2, TIME2)) -
                     DebtAmount(amount = 100,
                                unit = T2(CURRENCY1, TIME1)))
    }

    @Test
    fun divSameDebt()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = DebtAmount(amount = 2,
                                unit = T2(CURRENCY1, TIME1)) /
                     DebtAmount(amount = 2,
                                unit = T2(CURRENCY1, TIME1)))
    }

    @Test
    fun divOtherDebt()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = DebtAmount(amount = 2,
                                unit = T2(CURRENCY2, TIME2)) /
                     DebtAmount(amount = 200,
                                unit = T2(CURRENCY1, TIME1)))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = DebtAmount(amount = 2,
                                unit = T2(CURRENCY1, TIME1)) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY2),
            actual = DebtAmount(amount = 2,
                                unit = T2(CURRENCY2, TIME2)) /
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun timesSameFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = DebtAmount(amount = 2,
                                unit = T2(CURRENCY1, TIME1)) *
                     FrequencyAmount(amount = 2,
                                     unit = Fraction(ONE, TIME1)))
    }

    @Test
    fun timesOtherFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY2),
            actual = DebtAmount(amount = 2,
                                unit = T2(CURRENCY2, TIME2)) *
                     FrequencyAmount(amount = 20,
                                     unit = Fraction(ONE, TIME1)))
    }
}