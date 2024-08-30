package org.pcb.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Second.SECOND

class SpaceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = SpaceAmount(4, METER),
            actual = SpaceAmount(2, METER) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = SpaceAmount(1, METER),
            actual = SpaceAmount(2, METER) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameSpace()
    {
        assertEquals(
            expected = SpaceAmount(1, METER),
            actual = SpaceAmount(0, METER) + SpaceAmount(1, METER))
    }

    @Test
    fun plusOtherSpace()
    {
        assertEquals(
            expected = SpaceAmount(1, FOOT),
            actual = SpaceAmount(0, FOOT) + SpaceAmount(12, INCH))
    }

    @Test
    fun minusSameSpace()
    {
        assertEquals(
            expected = SpaceAmount(0, METER),
            actual = SpaceAmount(1, METER) - SpaceAmount(1, METER))
    }

    @Test
    fun minusOtherSpace()
    {
        assertEquals(
            expected = SpaceAmount(0, FOOT),
            actual = SpaceAmount(1, FOOT) - SpaceAmount(12, INCH))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = SpaceAmount(2, METER) / SpaceAmount(2, METER))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = SpaceAmount(2, FOOT) / SpaceAmount(24, INCH))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = SpaceAmount(2, METER) / TimeAmount(2, SECOND))
    }
}