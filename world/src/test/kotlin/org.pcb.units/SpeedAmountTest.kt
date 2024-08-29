package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.ScalarAmount
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.unit.imperial.Foot
import org.pcb.units.unit.imperial.Inch
import org.pcb.units.unit.metric.Meter
import org.pcb.units.unit.time.Minute
import org.pcb.units.unit.time.Second

class SpeedAmountTest
{
    @Test
    fun plusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(3, Meter, Second),
            actual = SpeedAmount(1, Meter, Second) + SpeedAmount(2, Meter, Second))
    }

    @Test
    fun plusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(3, Meter, Second),
            actual = SpeedAmount(2, Meter, Second) + SpeedAmount(60, Meter, Minute))
    }

    @Test
    fun minusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(1, Meter, Second),
            actual = SpeedAmount(3, Meter, Second) - SpeedAmount(2, Meter, Second))
    }

    @Test
    fun minusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(2, Meter, Second),
            actual = SpeedAmount(3, Meter, Second) - SpeedAmount(60, Meter, Minute))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = ScalarAmount(2),
            actual = SpeedAmount(8, Meter, Second) / SpeedAmount(4, Meter, Second))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = ScalarAmount(2),
            actual = SpeedAmount(2, Meter, Second) / SpeedAmount(60, Meter, Minute))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = SpaceAmount(6, Meter),
            actual = SpeedAmount(2, Meter, Second) * TimeAmount(3, Second))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = SpaceAmount(120, Meter),
            actual = SpeedAmount(2, Meter, Second) * TimeAmount(1, Minute))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = AccelerationAmount(2, Meter, Second, Second),
            actual = SpeedAmount(6, Meter, Second) / TimeAmount(3, Second))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = AccelerationAmount(2, Meter, Second, Minute),
            actual = SpeedAmount(6, Meter, Second) / TimeAmount(3, Minute))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = FrequencyAmount(2, Second),
            actual = SpeedAmount(6, Meter, Second) / SpaceAmount(3, Meter))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = FrequencyAmount(6, Second),
            actual = SpeedAmount(1, Foot, Second) / SpaceAmount(2, Inch))
    }

    // ... continue
}