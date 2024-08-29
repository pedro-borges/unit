package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.ScalarAmount
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.imperial.Foot.FOOT
import org.pcb.units.amount.imperial.Inch.INCH
import org.pcb.units.amount.metric.Meters.METER
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class SpeedAmountTest
{
    @Test
    fun plusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(3, METER, SECOND),
            actual = SpeedAmount(1, METER, SECOND) + SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun plusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(3, METER, SECOND),
            actual = SpeedAmount(2, METER, SECOND) + SpeedAmount(60, METER, MINUTE))
    }

    @Test
    fun minusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = SpeedAmount(3, METER, SECOND) - SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun minusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(2, METER, SECOND),
            actual = SpeedAmount(3, METER, SECOND) - SpeedAmount(60, METER, MINUTE))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = ScalarAmount(2),
            actual = SpeedAmount(8, METER, SECOND) / SpeedAmount(4, METER, SECOND))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = ScalarAmount(2),
            actual = SpeedAmount(2, METER, SECOND) / SpeedAmount(60, METER, MINUTE))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = SpaceAmount(6, METER),
            actual = SpeedAmount(2, METER, SECOND) * TimeAmount(3, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = SpaceAmount(120, METER),
            actual = SpeedAmount(2, METER, SECOND) * TimeAmount(1, MINUTE))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = AccelerationAmount(2, METER, SECOND, SECOND),
            actual = SpeedAmount(6, METER, SECOND) / TimeAmount(3, SECOND))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = AccelerationAmount(2, METER, SECOND, MINUTE),
            actual = SpeedAmount(6, METER, SECOND) / TimeAmount(3, MINUTE))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = FrequencyAmount(2, SECOND),
            actual = SpeedAmount(6, METER, SECOND) / SpaceAmount(3, METER))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = FrequencyAmount(6, SECOND),
            actual = SpeedAmount(1, FOOT, SECOND) / SpaceAmount(2, INCH))
    }

    // ... continue
}