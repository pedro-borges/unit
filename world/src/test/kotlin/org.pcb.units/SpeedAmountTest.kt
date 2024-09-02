package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.time.Minute.MINUTE

class SpeedAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = SpeedAmount(4, METER, SECOND),
            actual = SpeedAmount(2, METER, SECOND) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = SpeedAmount(2, METER, SECOND) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = SpeedAmount(0, METER, SECOND) +
                     SpeedAmount(1, METER, SECOND))
    }

    @Test
    fun plusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(1, FOOT, SECOND),
            actual = SpeedAmount(0, FOOT, SECOND) +
                     SpeedAmount(720, INCH, MINUTE))
    }

    @Test
    fun minusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(0, METER, SECOND),
            actual = SpeedAmount(1, METER, SECOND) -
                     SpeedAmount(1, METER, SECOND))
    }

    @Test
    fun minusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(0, FOOT, SECOND),
            actual = SpeedAmount(1, FOOT, SECOND) -
                     SpeedAmount(720, INCH, MINUTE))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = SpeedAmount(2, METER, SECOND) /
                     SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = SpeedAmount(2, FOOT, SECOND) /
                     SpeedAmount(1440, INCH, MINUTE))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = LengthAmount(4, METER),
            actual = SpeedAmount(2, METER, SECOND) *
                     TimeAmount(2, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = LengthAmount(240, METER),
            actual = SpeedAmount(2, METER, SECOND) *
                     TimeAmount(2, MINUTE))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND, SECOND),
            actual = SpeedAmount(2, METER, SECOND) /
                     TimeAmount(2, SECOND))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, MINUTE, MINUTE),
            actual = SpeedAmount(2, METER, MINUTE) /
                     TimeAmount(120, SECOND))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = SpeedAmount(2, METER, SECOND) /
                     LengthAmount(2, METER))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = SpeedAmount(2, FOOT, SECOND) /
                     LengthAmount(24, INCH))
    }
}