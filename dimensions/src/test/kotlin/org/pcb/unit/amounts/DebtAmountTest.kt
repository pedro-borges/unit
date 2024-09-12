package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.CURRENCY2
import org.pcb.unit.units.test.NUMBER1
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class DebtAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = DebtAmount(amount = 2 * 2,
                                  unit = U2(CURRENCY1, TIME1)),
            actual = DebtAmount(amount = 2,
                                unit = U2(CURRENCY1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = DebtAmount(amount = 1,
                                  unit = U2(CURRENCY1, TIME1)),
            actual = DebtAmount(amount = 2,
                                unit = U2(CURRENCY1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameDebt()
    {
        assertEquals(
            expected = DebtAmount(amount = 1,
                                  unit = U2(CURRENCY1, TIME1)),
            actual = DebtAmount(amount = 0,
                                unit = U2(CURRENCY1, TIME1)) +
                     DebtAmount(amount = 1,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun plusOtherDebt()
    {
        assertEquals(
            expected = DebtAmount(amount = 1,
                                  unit = U2(CURRENCY2, TIME2)),
            actual = DebtAmount(amount = 0,
                                unit = U2(CURRENCY2, TIME2)) +
                     DebtAmount(amount = 100,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun minusSameDebt()
    {
        assertEquals(
            expected = DebtAmount(amount = 0,
                                  unit = U2(CURRENCY1, TIME1)),
            actual = DebtAmount(amount = 1,
                                unit = U2(CURRENCY1, TIME1)) -
                     DebtAmount(amount = 1,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun minusOtherDebt()
    {
        assertEquals(
            expected = DebtAmount(amount = 0,
                                  unit = U2(CURRENCY2, TIME2)),
            actual = DebtAmount(amount = 1,
                                unit = U2(CURRENCY2, TIME2)) -
                     DebtAmount(amount = 100,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun divSameDebt()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = DebtAmount(amount = 2,
                                unit = U2(CURRENCY1, TIME1)) /
                     DebtAmount(amount = 2,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun divOtherDebt()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = DebtAmount(amount = 2,
                                unit = U2(CURRENCY2, TIME2)) /
                     DebtAmount(amount = 200,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = DebtAmount(amount = 2,
                                unit = U2(CURRENCY1, TIME1)) /
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
                                unit = U2(CURRENCY2, TIME2)) /
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
                                unit = U2(CURRENCY1, TIME1)) *
                     FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun timesOtherFrequency()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY2),
            actual = DebtAmount(amount = 2,
                                unit = U2(CURRENCY2, TIME2)) *
                     FrequencyAmount(amount = 20,
                                     unit = NUMBER1 to TIME1))
    }
}