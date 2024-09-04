package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.scalar.Natural.NATURAL
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
                                       unit = NATURAL / TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       unit = NATURAL / TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0 + 1,
                                       unit = NATURAL / TIME1),
            actual = FrequencyAmount(amount = 0,
                                     unit = NATURAL / TIME1) +
                     FrequencyAmount(amount = 1,
                                     unit = NATURAL / TIME1))
    }

    @Test
    fun plusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0 + 1,
                                       unit = NATURAL / TIME1),
            actual = FrequencyAmount(amount = 0,
                                     unit = NATURAL / TIME1) +
                     FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME2))
    }

    @Test
    fun minusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1 - 1,
                                       unit = NATURAL / TIME1),
            actual = FrequencyAmount(amount = 1,
                                     unit = NATURAL / TIME1) -
                     FrequencyAmount(amount = 1,
                                     unit = NATURAL / TIME1))
    }

    @Test
    fun minusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1 - 1,
                                       unit = NATURAL / TIME1),
            actual = FrequencyAmount(amount = 1,
                                     unit = NATURAL / TIME1) -
                     FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME2))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) /
                     FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = BigDecimal(2 / 2),
            actual = FrequencyAmount(amount = 1,
                                     unit = NATURAL / TIME1) /
                     FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME2))
    }

    @Test
    fun timesSpace()
    {
        assertEquals<SpeedAmount<out Unit<Length>, TIME1>>(
            expected = SpeedAmount(amount = 2 / 2,
                                   unit = LENGTH1 / TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals<AccelerationAmount<out Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 / TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals<AccelerationAmount<out Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) *
                     SpeedAmount(amount = 2 * 2,
                                 unit = LENGTH1 / TIME2))
    }

    @Test
    fun timesSameDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 / 2,
                                      unit = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) *
                     DebtAmount(amount = 2,
                                unit = CURRENCY1 * TIME1))
    }

    @Test
    fun timesOtherDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 / 2,
                                      unit = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) *
                     DebtAmount(amount = 1,
                                unit = CURRENCY1 * TIME2))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals<RentAmount<out Unit<Currency>, TIME1>>(
            expected = RentAmount(amount = 2 / 2,
                                  unit = CURRENCY1 / TIME1),
            actual = FrequencyAmount(amount = 2,
                                     unit = NATURAL / TIME1) *
                     CurrencyAmount(amount = 2,
                                    unit = CURRENCY1))
    }
}