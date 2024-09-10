package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.CURRENCY1
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2


class FrequencyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 * 2,
                                       unit = NUMERIC to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = FrequencyAmount(amount = 0,
                                     unit = NUMERIC to TIME1) +
                     FrequencyAmount(amount = 1,
                                     unit = NUMERIC to TIME1))
    }

    @Test
    fun plusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = FrequencyAmount(amount = 0,
                                     unit = NUMERIC to TIME1) +
                     FrequencyAmount(amount = 10,
                                     unit = NUMERIC to TIME2))
    }

    @Test
    fun minusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0,
                                       unit = NUMERIC to TIME1),
            actual = FrequencyAmount(amount = 1,
                                     unit = NUMERIC to TIME1) -
                     FrequencyAmount(amount = 1,
                                     unit = NUMERIC to TIME1))
    }

    @Test
    fun minusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0,
                                       unit = NUMERIC to TIME1),
            actual = FrequencyAmount(amount = 1,
                                     unit = NUMERIC to TIME1) -
                     FrequencyAmount(amount = 10,
                                     unit = NUMERIC to TIME2))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) /
                     FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) /
                     FrequencyAmount(amount = 20,
                                     unit = NUMERIC to TIME2))
    }

    @Test
    fun timesSpace()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = DISTANCE1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) *
                     DistanceAmount(amount = 2,
                                  unit = DISTANCE1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = DISTANCE1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = DISTANCE1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) *
                     SpeedAmount(amount = 20,
                                 unit = DISTANCE1 to TIME2))
    }

    @Test
    fun timesSameDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) *
                     DebtAmount(amount = 2,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun timesOtherDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 1,
                                      unit = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME2) *
                     DebtAmount(amount = 20,
                                unit = U2(CURRENCY1, TIME1)))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals(
            expected = RentAmount(amount = 1,
                                  unit = CURRENCY1 to TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1) *
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }
}