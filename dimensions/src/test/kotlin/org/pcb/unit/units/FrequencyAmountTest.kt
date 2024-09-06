package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.T2
import org.pcb.unit.units.scalar.Numeric.ONE
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2


class FrequencyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 * 2,
                                       unit = ONE to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = ONE to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = ONE to TIME1),
            actual = FrequencyAmount(amount = 0,
                                     unit = ONE to TIME1) +
                     FrequencyAmount(amount = 1,
                                     unit = ONE to TIME1))
    }

    @Test
    fun plusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = ONE to TIME1),
            actual = FrequencyAmount(amount = 0,
                                     unit = ONE to TIME1) +
                     FrequencyAmount(amount = 10,
                                     unit = ONE to TIME2))
    }

    @Test
    fun minusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0,
                                       unit = ONE to TIME1),
            actual = FrequencyAmount(amount = 1,
                                     unit = ONE to TIME1) -
                     FrequencyAmount(amount = 1,
                                     unit = ONE to TIME1))
    }

    @Test
    fun minusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0,
                                       unit = ONE to TIME1),
            actual = FrequencyAmount(amount = 1,
                                     unit = ONE to TIME1) -
                     FrequencyAmount(amount = 10,
                                     unit = ONE to TIME2))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) /
                     FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) /
                     FrequencyAmount(amount = 20,
                                     unit = ONE to TIME2))
    }

    @Test
    fun timesSpace()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = LENGTH1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = LENGTH1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = LENGTH1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) *
                     SpeedAmount(amount = 20,
                                 unit = LENGTH1 to TIME2))
    }

    @Test
    fun timesSameDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) *
                     DebtAmount(amount = 2,
                                unit = T2(CURRENCY1, TIME1)))
    }

    @Test
    fun timesOtherDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME2) *
                     DebtAmount(amount = 20,
                                unit = T2(CURRENCY1, TIME1)))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = ONE to TIME1) *
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }
}