package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
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
            expected = FrequencyAmount(2 * 2, TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = FrequencyAmount(2 / 2, TIME1),
            actual = FrequencyAmount(2, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(0 + 1, TIME1),
            actual = FrequencyAmount(0, TIME1) +
                     FrequencyAmount(1, TIME1))
    }

    @Test
    fun plusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(0 + 1, TIME1),
            actual = FrequencyAmount(0, TIME1) +
                     FrequencyAmount(2, TIME2))
    }

    @Test
    fun minusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(1 - 1, TIME1),
            actual = FrequencyAmount(1, TIME1) -
                     FrequencyAmount(1, TIME1))
    }

    @Test
    fun minusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(1 - 1, TIME1),
            actual = FrequencyAmount(1, TIME1) -
                     FrequencyAmount(2, TIME2))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = FrequencyAmount(2, TIME1) /
                     FrequencyAmount(2, TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = FrequencyAmount(1, TIME1) /
                     FrequencyAmount(2, TIME2))
    }

    @Test
    fun timesSpace()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1 to TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     SpeedAmount(2, LENGTH1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     SpeedAmount(2 * 2, LENGTH1 to TIME2))
    }

    @Test
    fun timesSameDebt()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = FrequencyAmount(2, TIME1) *
                     DebtAmount(2, CURRENCY1 * TIME1))
    }

    @Test
    fun timesOtherDebt()
    {
        assertEquals(
            expected = CurrencyAmount(2 / 2, CURRENCY1),
            actual = FrequencyAmount(2, TIME1) *
                     DebtAmount(1, CURRENCY1 * TIME2))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals(
            expected = RentAmount(2 / 2, CURRENCY1 to TIME1),
            actual = FrequencyAmount(2, TIME1) *
                     CurrencyAmount(2, CURRENCY1))
    }
}