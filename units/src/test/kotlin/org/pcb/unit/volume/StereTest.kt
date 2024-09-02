package org.pcb.unit.volume

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.amount.AreaAmount
import org.pcb.unit.amount.base.Meter
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.space.DeciMeter
import org.pcb.unit.amount.space.DeciMeter.DECIMETER
import org.pcb.unit.amount.space.Liter
import org.pcb.unit.amount.space.Stere

class StereTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = Stere(4),
            actual = Stere(2) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = Stere(1),
            actual = Stere(2) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameVolume()
    {
        assertEquals(
            expected = Stere(1),
            actual = Stere(0) +
                     Stere(1))
    }

    @Test
    fun plusOtherVolume()
    {
        assertEquals(
            expected = Stere(1),
            actual = Stere(0) +
                     Liter(100 * 100 * 100))
    }

    @Test
    fun minusSameVolume()
    {
        assertEquals(
            expected = Stere(0),
            actual = Stere(1) -
                     Stere(1))
    }

    @Test
    fun minusOtherVolume()
    {
        assertEquals(
            expected = Stere(0),
            actual = Stere(1) -
                     Liter(100 * 100 * 100))
    }

    @Test
    fun divSameVolume()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = Stere(2) /
                     Stere(2))
    }

    @Test
    fun divOtherVolume()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = Stere(2) /
                     Liter(2 * 100 * 100 * 100))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = AreaAmount(1, METER, METER),
            actual = Stere(2 ) /
                     Meter(2))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = AreaAmount(1, METER, METER),
            actual = Stere(2) /
                     DeciMeter(2 * 100))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = Meter(1),
            actual = Stere(2) /
                     AreaAmount(2, METER, METER))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = Meter(1),
            actual = Stere(2) /
                     AreaAmount(2 * 100 * 100, DECIMETER, DECIMETER))
    }
}