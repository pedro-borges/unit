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

class AreaAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = AreaAmount(4, METER, METER),
            actual = AreaAmount(2, METER, METER) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = AreaAmount(1, METER, METER),
            actual = AreaAmount(2, METER, METER) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameArea()
    {
        assertEquals(
            expected = AreaAmount(1, METER, METER),
            actual = AreaAmount(0, METER, METER) + AreaAmount(1, METER, METER))
    }

    @Test
    fun plusOtherArea()
    {
        assertEquals(
            expected = AreaAmount(1, FOOT, FOOT),
            actual = AreaAmount(0, FOOT, FOOT) + AreaAmount(12 * 12, INCH, INCH))
    }

    @Test
    fun minusSameArea()
    {
        assertEquals(
            expected = AreaAmount(0, METER, METER),
            actual = AreaAmount(1, METER, METER) - AreaAmount(1, METER, METER))
    }

    @Test
    fun minusOtherArea()
    {
        assertEquals(
            expected = AreaAmount(0, FOOT, FOOT),
            actual = AreaAmount(1, FOOT, FOOT) - AreaAmount(12 * 12, INCH, INCH))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = AreaAmount(2, METER, METER) / AreaAmount(2, METER, METER))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = AreaAmount(2, FOOT, FOOT) / AreaAmount(2 * 12 * 12, INCH, INCH))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = LengthAmount(1, METER),
            actual = AreaAmount(2, METER, METER) / LengthAmount(2, METER))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = LengthAmount(1, FOOT),
            actual = AreaAmount(2, FOOT, FOOT) / LengthAmount(2 * 12, INCH))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals(
            expected = VolumeAmount(4, METER, METER, METER),
            actual = AreaAmount(2, METER, METER) * LengthAmount(2, METER))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals(
            expected = VolumeAmount(4, FOOT, FOOT, FOOT),
            actual = AreaAmount(2, FOOT, FOOT) * LengthAmount(2 * 12, INCH))
    }
}