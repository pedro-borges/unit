package org.pcb.units.volume

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.AreaAmount
import org.pcb.units.amount.base.Meter
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.DeciMeter
import org.pcb.units.amount.space.DeciMeter.DECIMETER
import org.pcb.units.amount.space.Liter
import org.pcb.units.amount.space.Stere

class LiterTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = Liter(4),
            actual = Liter(2) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = Liter(1),
            actual = Liter(2) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameVolume()
    {
        assertEquals(
            expected = Liter(1),
            actual = Liter(0) +
                     Liter(1))
    }

    @Test
    fun plusOtherVolume()
    {
        assertEquals(
            expected = Liter(100 * 100 * 100),
            actual = Liter(0) +
                     Stere(1))
    }

    @Test
    fun minusSameVolume()
    {
        assertEquals(
            expected = Liter(0),
            actual = Liter(1) -
                     Liter(1))
    }

    @Test
    fun minusOtherVolume()
    {
        assertEquals(
            expected = Liter(0),
            actual = Liter(1000000) -
                     Stere(1))
    }

    @Test
    fun divSameVolume()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = Liter(2) /
                     Liter(2))
    }

    @Test
    fun divOtherVolume()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = Liter(2 * 100 * 100 * 100) /
                     Stere(2))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = AreaAmount(1, DECIMETER, DECIMETER),
            actual = Liter(2 ) /
                     DeciMeter(2))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = AreaAmount(1, DECIMETER, DECIMETER),
            actual = Liter(2 * 100) /
                     Meter(2))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = DeciMeter(1),
            actual = Liter(2) /
                     AreaAmount(2, DECIMETER, DECIMETER))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = DeciMeter(1),
            actual = Liter(2 * 100 * 100) /
                     AreaAmount(2, METER, METER))
    }
}