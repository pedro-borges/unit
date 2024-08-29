package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.PaceAmount
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.imperial.Foot.FOOT
import org.pcb.units.amount.imperial.Inch.INCH
import org.pcb.units.amount.metric.Meters.METER
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class PaceAmountTest
{
    @Test
    fun plusSamePace()
    {
        assertEquals(
            expected = PaceAmount(1, SECOND, METER),
            actual = PaceAmount(0, SECOND, METER) + PaceAmount(1, SECOND, METER))
    }

    @Test
    fun plusOtherPace()
    {
        assertEquals(
            expected = PaceAmount(1, MINUTE, FOOT),
            actual = PaceAmount(0, MINUTE, FOOT) + PaceAmount(5, SECOND, INCH))
    }

    @Test
    fun minusSamePace()
    {
        assertEquals(
            expected = PaceAmount(0, SECOND, METER),
            actual = PaceAmount(1, SECOND, METER) - PaceAmount(1, SECOND, METER))
    }

    @Test
    fun minusOtherPace()
    {
        assertEquals(
            expected = PaceAmount(0, MINUTE, FOOT),
            actual = PaceAmount(1, MINUTE, FOOT) - PaceAmount(5, SECOND, INCH))
    }

    @Test
    fun divSamePace()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = PaceAmount(2, SECOND, METER) / PaceAmount(2, SECOND, METER))
    }

    @Test
    fun divOtherPace()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = PaceAmount(2, MINUTE, FOOT) / PaceAmount(10, SECOND, INCH))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(4),
            actual = PaceAmount(2, SECOND, METER) * SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(4),
            actual = PaceAmount(2, MINUTE, FOOT) * SpeedAmount(10, INCH, SECOND))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = TimeAmount(4, SECOND),
            actual = PaceAmount(2, SECOND, METER) * SpaceAmount(2, METER))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = TimeAmount(4, SECOND),
            actual = PaceAmount(2, SECOND, FOOT) * SpaceAmount(24, INCH))
    }

    // ... continue
}