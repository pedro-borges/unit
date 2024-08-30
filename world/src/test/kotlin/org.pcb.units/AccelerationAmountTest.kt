package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.length.Foot.FOOT
import org.pcb.units.amount.length.Inch.INCH
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.domain.Time

class AccelerationAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<AccelerationAmount<METER, out Unit<Time>>>(
            expected = AccelerationAmount(1, METER, SECOND, SECOND),
            actual = AccelerationAmount(60, METER, SECOND, MINUTE))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = AccelerationAmount(4, METER, SECOND, SECOND),
            actual = AccelerationAmount(2, METER, SECOND, SECOND) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND, SECOND),
            actual = AccelerationAmount(2, METER, SECOND, SECOND) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND, SECOND),
            actual = AccelerationAmount(0, METER, SECOND, SECOND) + AccelerationAmount(1, METER, SECOND, SECOND))
    }

    @Test
    fun plusOtherAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(1, FOOT, SECOND, SECOND),
            actual = AccelerationAmount(0, FOOT, SECOND, SECOND) + AccelerationAmount(12 * 60 * 60, INCH, MINUTE, MINUTE))
    }

    @Test
    fun minusSameAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(0, METER, SECOND, SECOND),
            actual = AccelerationAmount(1, METER, SECOND, SECOND) - AccelerationAmount(1, METER, SECOND, SECOND))
    }

    @Test
    fun minusOtherAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(0, FOOT, SECOND, SECOND),
            actual = AccelerationAmount(1, FOOT, SECOND, SECOND) - AccelerationAmount(12 * 60 * 60, INCH, MINUTE, MINUTE))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = AccelerationAmount(2, METER, SECOND, SECOND) / AccelerationAmount(2, METER, SECOND, SECOND))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = AccelerationAmount(2, FOOT, SECOND, SECOND) / AccelerationAmount(2 * 12 * 60 * 60, INCH, MINUTE, MINUTE))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = SpeedAmount(4, METER, SECOND),
            actual = AccelerationAmount(2, METER, SECOND, SECOND) * TimeAmount(2, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = SpeedAmount(240, METER, SECOND),
            actual = AccelerationAmount(2, METER, SECOND, SECOND) * TimeAmount(2, MINUTE))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = AccelerationAmount(2, METER, SECOND, SECOND) / SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(1, SECOND),
            actual = AccelerationAmount(2, FOOT, SECOND, SECOND) / SpeedAmount(2 * 12 * 60, INCH, MINUTE))
    }

    @Test
    fun timesMass()
    {
        assertEquals(
            expected = ForceAmount(4, KILOGRAM, METER, SECOND, SECOND),
            actual = AccelerationAmount(2, METER, SECOND, SECOND) * MassAmount(2, KILOGRAM))
    }
}