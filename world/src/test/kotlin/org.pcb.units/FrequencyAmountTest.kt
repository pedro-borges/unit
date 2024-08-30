package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.CurrencyAmount
import org.pcb.units.amount.DebtAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.RentAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.finance.Gbp.GBP
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Day.DAY
import org.pcb.units.amount.time.Minute.MINUTE

class FrequencyAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = FrequencyAmount(4, SECOND),
            actual = FrequencyAmount(2, SECOND) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = FrequencyAmount(2, SECOND) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = FrequencyAmount(0, SECOND) + FrequencyAmount(1, SECOND))
    }

    @Test
    fun plusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(60, SECOND),
            actual = FrequencyAmount(0, SECOND) + FrequencyAmount(1, MINUTE))
    }

    @Test
    fun minusSameFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(0, SECOND),
            actual = FrequencyAmount(1, SECOND) - FrequencyAmount(1, SECOND))
    }

    @Test
    fun minusOtherFrequency()
    {
        assertEquals(
            expected = FrequencyAmount(0, SECOND),
            actual = FrequencyAmount(60, SECOND) - FrequencyAmount(1, MINUTE))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = FrequencyAmount(2, SECOND) / FrequencyAmount(2, SECOND))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = FrequencyAmount(120, SECOND) / FrequencyAmount(2, MINUTE))
    }

    @Test
    fun timesSpace()
    {
        assertEquals(
            expected = SpeedAmount(4, METER, SECOND),
            actual = FrequencyAmount(2, SECOND) * LengthAmount(2, METER))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(4, METER, SECOND, SECOND),
            actual = FrequencyAmount(2, SECOND) * SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(4, METER, MINUTE, MINUTE),
            actual = FrequencyAmount(2, MINUTE) * SpeedAmount(2 * 60, METER, SECOND))
    }

    @Test
    fun timesSameDebt()
    {
        assertEquals(
            expected = CurrencyAmount(1, GBP),
            actual = FrequencyAmount(2, SECOND) * DebtAmount(2, GBP, SECOND))
    }

    @Test
    fun timesOtherDebt()
    {
        assertEquals(
            expected = CurrencyAmount(1, GBP),
            actual = FrequencyAmount(2 * 60, MINUTE) * DebtAmount(2, GBP, SECOND))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals(
            expected = RentAmount(4, GBP, DAY),
            actual = FrequencyAmount(2, DAY) * CurrencyAmount(2, GBP))
    }
}