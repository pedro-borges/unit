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
            expected = FrequencyAmount(amount = 2 * 2,
                                       minor = TIME1),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       minor = TIME1),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0 + 1,
                                       minor = TIME1),
            actual = FrequencyAmount(amount = 0,
                                     minor = TIME1) +
                     FrequencyAmount(amount = 1,
                                     minor = TIME1))
    }

    @Test
    fun plusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 0 + 1,
                                       minor = TIME1),
            actual = FrequencyAmount(amount = 0,
                                     minor = TIME1) +
                     FrequencyAmount(amount = 2,
                                     minor = TIME2))
    }

    @Test
    fun minusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1 - 1,
                                       minor = TIME1),
            actual = FrequencyAmount(amount = 1,
                                     minor = TIME1) -
                     FrequencyAmount(amount = 1,
                                     minor = TIME1))
    }

    @Test
    fun minusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1 - 1,
                                       minor = TIME1),
            actual = FrequencyAmount(amount = 1,
                                     minor = TIME1) -
                     FrequencyAmount(amount = 2,
                                     minor = TIME2))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) /
                     FrequencyAmount(amount = 2,
                                     minor = TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = FrequencyAmount(amount = 1,
                                     minor = TIME1) /
                     FrequencyAmount(amount = 2,
                                     minor = TIME2))
    }

    @Test
    fun timesSpace()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 / 2,
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) *
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) *
                     SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) *
                     SpeedAmount(amount = 2 * 2,
                                 major = LENGTH1,
                                 minor = TIME2))
    }

    @Test
    fun timesSameDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 / 2,
                                      major = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) *
                     DebtAmount(amount = 2,
                                major = CURRENCY1 * TIME1))
    }

    @Test
    fun timesOtherDebt()
    {
        assertEquals(
            expected = CurrencyAmount(amount = 2 / 2,
                                      major = CURRENCY1),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) *
                     DebtAmount(amount = 1,
                                major = CURRENCY1 * TIME2))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals(
            expected = RentAmount(amount = 2 / 2,
                                  major = CURRENCY1,
                                  minor = TIME1),
            actual = FrequencyAmount(amount = 2,
                                     minor = TIME1) *
                     CurrencyAmount(amount = 2,
                                    major = CURRENCY1))
    }
}