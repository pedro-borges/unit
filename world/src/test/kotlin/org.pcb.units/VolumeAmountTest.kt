package org.pcb.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.AreaAmount
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.VolumeAmount
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.length.Foot.FOOT
import org.pcb.units.amount.length.Inch.INCH
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount

class VolumeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = VolumeAmount(4, METER, METER, METER),
            actual = VolumeAmount(2, METER, METER, METER) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = VolumeAmount(1, METER, METER, METER),
            actual = VolumeAmount(2, METER, METER, METER) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameVolume()
    {
        assertEquals(
            expected = VolumeAmount(1, METER, METER, METER),
            actual = VolumeAmount(0, METER, METER, METER) +
                     VolumeAmount(1, METER, METER, METER))
    }

    @Test
    fun plusOtherVolume()
    {
        assertEquals(
            expected = VolumeAmount(1, FOOT, FOOT, FOOT),
            actual = VolumeAmount(0, FOOT, FOOT, FOOT) +
                     VolumeAmount(12 * 12 * 12, INCH, INCH, INCH))
    }

    @Test
    fun minusSameVolume()
    {
        assertEquals(
            expected = VolumeAmount(0, METER, METER, METER),
            actual = VolumeAmount(1, METER, METER, METER) -
                     VolumeAmount(1, METER, METER, METER))
    }

    @Test
    fun minusOtherVolume()
    {
        assertEquals(
            expected = VolumeAmount(0, FOOT, FOOT, FOOT),
            actual = VolumeAmount(1, FOOT, FOOT, FOOT) -
                     VolumeAmount(12 * 12 * 12, INCH, INCH, INCH))
    }

    @Test
    fun divSameVolume()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = VolumeAmount(2, METER, METER, METER) /
                     VolumeAmount(2, METER, METER, METER))
    }

    @Test
    fun divOtherVolume()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = VolumeAmount(2, FOOT, FOOT, FOOT) /
                     VolumeAmount(2 * 12 * 12 * 12, INCH, INCH, INCH))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = AreaAmount(1, METER, METER),
            actual = VolumeAmount(2, METER, METER, METER) /
                     LengthAmount(2, METER))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = AreaAmount(1, FOOT, FOOT),
            actual = VolumeAmount(2, FOOT, FOOT, FOOT) /
                     LengthAmount(2 * 12, INCH))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = LengthAmount(1, METER),
            actual = VolumeAmount(2, METER, METER, METER) /
                     AreaAmount(2, METER, METER))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = LengthAmount(1, FOOT),
            actual = VolumeAmount(2, FOOT, FOOT, FOOT) /
                     AreaAmount(2 * 12 * 12, INCH, INCH))
    }
}