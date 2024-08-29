package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class SpeedAmountTest
{
    @Test
    fun plusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = SpeedAmount(0, METER, SECOND) + SpeedAmount(1, METER, SECOND))
    }

    @Test
    fun plusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(1, FOOT, MINUTE),
            actual = SpeedAmount(0, FOOT, MINUTE) + SpeedAmount(0.2, INCH, SECOND))
    }

    @Test
    fun minusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(0, METER, SECOND),
            actual = SpeedAmount(1, METER, SECOND) - SpeedAmount(1, METER, SECOND))
    }

    @Test
    fun minusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(0, FOOT, MINUTE),
            actual = SpeedAmount(1, FOOT, MINUTE) - SpeedAmount(0.2, INCH, SECOND))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = SpeedAmount(2, METER, SECOND) / SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = SpeedAmount(2, FOOT, MINUTE) / SpeedAmount(0.4, INCH, SECOND))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = SpaceAmount(4, METER),
            actual = SpeedAmount(2, METER, SECOND) * TimeAmount(2, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = SpaceAmount(240, METER),
            actual = SpeedAmount(2, METER, SECOND) * TimeAmount(2, MINUTE))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND, SECOND),
            actual = SpeedAmount(2, METER, SECOND) / TimeAmount(2, SECOND))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND, MINUTE),
            actual = SpeedAmount(2, METER, SECOND) / TimeAmount(2, MINUTE))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = SpeedAmount(2, METER, SECOND) / SpaceAmount(2, METER))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = SpeedAmount(2, FOOT, SECOND) / SpaceAmount(24, INCH))
    }
}