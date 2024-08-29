package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.CurrencyAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.RentAmount
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.finance.Gbp.GBP
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.space.metric.Meter.METER
import org.pcb.units.amount.time.Day.DAY
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class FrequencyAmountTest
{
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
            actual = FrequencyAmount(2, SECOND) * SpaceAmount(2, METER))
    }

    @Test
    fun timesSpeed()
    {
        assertEquals(
            expected = AccelerationAmount(4, METER, SECOND, SECOND),
            actual = FrequencyAmount(2, SECOND) * SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun timesCurrency()
    {
        assertEquals(
            expected = RentAmount(4, GBP, DAY),
            actual = FrequencyAmount(2, DAY) * CurrencyAmount(2, GBP))
    }
}