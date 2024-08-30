package org.pcb.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Second.SECOND

class LengthAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LengthAmount(4, METER),
            actual = LengthAmount(2, METER) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LengthAmount(1, METER),
            actual = LengthAmount(2, METER) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameSpace()
    {
        assertEquals(
            expected = LengthAmount(1, METER),
            actual = LengthAmount(0, METER) + LengthAmount(1, METER))
    }

    @Test
    fun plusOtherSpace()
    {
        assertEquals(
            expected = LengthAmount(1, FOOT),
            actual = LengthAmount(0, FOOT) + LengthAmount(12, INCH))
    }

    @Test
    fun minusSameSpace()
    {
        assertEquals(
            expected = LengthAmount(0, METER),
            actual = LengthAmount(1, METER) - LengthAmount(1, METER))
    }

    @Test
    fun minusOtherSpace()
    {
        assertEquals(
            expected = LengthAmount(0, FOOT),
            actual = LengthAmount(1, FOOT) - LengthAmount(12, INCH))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = LengthAmount(2, METER) / LengthAmount(2, METER))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = LengthAmount(2, FOOT) / LengthAmount(24, INCH))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = LengthAmount(2, METER) / TimeAmount(2, SECOND))
    }
}