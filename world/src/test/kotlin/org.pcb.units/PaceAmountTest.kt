package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.PaceAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class PaceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = PaceAmount(4, SECOND, METER),
            actual = PaceAmount(2, SECOND, METER) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PaceAmount(1, SECOND, METER),
            actual = PaceAmount(2, SECOND, METER) / PercentAmount(2 * 100))
    }

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
        actual = PaceAmount(0, MINUTE, FOOT) + PaceAmount(60 / 12, SECOND, INCH))
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
            actual = PaceAmount(1, MINUTE, FOOT) - PaceAmount(60 / 12, SECOND, INCH))
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
            actual = PaceAmount(2, MINUTE, FOOT) / PaceAmount(2 * 60 / 12, SECOND, INCH))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = PaceAmount(2, SECOND, METER) * SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = PaceAmount(2, MINUTE, FOOT) * SpeedAmount(2 * 60 / 12, INCH, SECOND))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = TimeAmount(4, SECOND),
            actual = PaceAmount(2, SECOND, METER) * LengthAmount(2, METER))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = TimeAmount(4, SECOND),
            actual = PaceAmount(2, SECOND, FOOT) * LengthAmount(24, INCH))
    }
}