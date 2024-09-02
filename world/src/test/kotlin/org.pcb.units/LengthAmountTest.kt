package org.pcb.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.AreaAmount
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.VolumeAmount
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH

class LengthAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LengthAmount(4, METER),
            actual = LengthAmount(2, METER) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LengthAmount(1, METER),
            actual = LengthAmount(2, METER) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameLength()
    {
        assertEquals(
            expected = LengthAmount(1, METER),
            actual = LengthAmount(0, METER) +
                     LengthAmount(1, METER))
    }

    @Test
    fun plusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(1, FOOT),
            actual = LengthAmount(0, FOOT) +
                     LengthAmount(12, INCH))
    }

    @Test
    fun minusSameLength()
    {
        assertEquals(
            expected = LengthAmount(0, METER),
            actual = LengthAmount(1, METER) -
                     LengthAmount(1, METER))
    }

    @Test
    fun minusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(0, FOOT),
            actual = LengthAmount(1, FOOT) -
                     LengthAmount(12, INCH))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = LengthAmount(2, METER) /
                     LengthAmount(2, METER))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = LengthAmount(2, FOOT) /
                     LengthAmount(2 * 12, INCH))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = LengthAmount(2, METER) /
                     TimeAmount(2, SECOND))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals(
            expected = AreaAmount(4, METER, METER),
            actual = LengthAmount(2, METER) *
                     LengthAmount(2, METER))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals(
            expected = AreaAmount(4, FOOT, FOOT),
            actual = LengthAmount(2, FOOT) *
                     LengthAmount(2 * 12, INCH))
    }

    @Test
    fun timesSameArea()
    {
        assertEquals(
            expected = VolumeAmount(4, METER, METER, METER),
            actual = LengthAmount(2, METER) *
                     AreaAmount(2, METER, METER))
    }

    @Test
    fun timesOtherArea()
    {
        assertEquals(
            expected = VolumeAmount(4, FOOT, FOOT, FOOT),
            actual = LengthAmount(2, FOOT) *
                     AreaAmount(2 * 12 * 12, INCH, INCH))
    }
}