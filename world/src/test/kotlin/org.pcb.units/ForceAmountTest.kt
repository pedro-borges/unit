package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.EnergyAmount
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.MomentumAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.length.Foot.FOOT
import org.pcb.units.amount.length.Inch.INCH
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.mechanics.Newton.NEWTON
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.domain.Time

class ForceAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ForceAmount<KILOGRAM, METER, out Unit<Time>>>(
            expected = ForceAmount(1, KILOGRAM, METER, SECOND, SECOND),
            actual = ForceAmount(60, KILOGRAM, METER, SECOND, MINUTE))
    }

    @Test
    fun ownUnit()
    {
        assertEquals(
            expected = ForceAmount(1, KILOGRAM, METER, SECOND, SECOND),
            actual = ForceAmount(1, NEWTON))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ForceAmount(4, NEWTON),
            actual = ForceAmount(2, NEWTON) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ForceAmount(1, NEWTON),
            actual = ForceAmount(2, NEWTON) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameForce()
    {
        assertEquals(
            expected = ForceAmount(1, NEWTON),
            actual = ForceAmount(0, NEWTON) + ForceAmount(1, NEWTON))
    }

    @Test
    fun plusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(1, POUND, FOOT, SECOND, SECOND),
            actual = ForceAmount(0, POUND, FOOT, SECOND, SECOND) +
                     ForceAmount(16 * 12 * 60 * 60, OUNCE, INCH, MINUTE, MINUTE))
    }

    @Test
    fun minusSameForce()
    {
        assertEquals(
            expected = ForceAmount(0, NEWTON),
            actual = ForceAmount(1, NEWTON) - ForceAmount(1, NEWTON))
    }

    @Test
    fun minusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(0, POUND, FOOT, SECOND, SECOND),
            actual = ForceAmount(1, POUND, FOOT, SECOND, SECOND) -
                     ForceAmount(16 * 12 * 60 * 60, OUNCE, INCH, MINUTE, MINUTE))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ForceAmount(2, NEWTON) / ForceAmount(2, NEWTON))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ForceAmount(2, POUND, FOOT, SECOND, SECOND) /
                     ForceAmount(2 * 16 * 12 * 60 * 60, OUNCE, INCH, MINUTE, MINUTE))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND, SECOND),
            actual = ForceAmount(2, KILOGRAM, METER, SECOND, SECOND) / MassAmount(2, KILOGRAM))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND, SECOND),
            actual = ForceAmount(2, POUND, METER, SECOND, SECOND) / MassAmount(2 * 16, OUNCE))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = MassAmount(1, KILOGRAM),
            actual = ForceAmount(2, KILOGRAM, METER, SECOND, SECOND) / AccelerationAmount(2, METER, SECOND, SECOND))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = MassAmount(1, KILOGRAM),
            actual = ForceAmount(2, KILOGRAM, FOOT, SECOND, SECOND) / AccelerationAmount(2 * 12 * 60 * 60, INCH, MINUTE, MINUTE))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MomentumAmount(4, KILOGRAM, METER, SECOND),
            actual = ForceAmount(2, KILOGRAM, METER, SECOND, SECOND) * TimeAmount(2, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MomentumAmount(4, KILOGRAM, FOOT, MINUTE),
            actual = ForceAmount(2, KILOGRAM, FOOT, MINUTE, MINUTE) * TimeAmount(2 * 60, SECOND))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = EnergyAmount(4, KILOGRAM, METER, METER, SECOND, SECOND),
            actual = ForceAmount(2, KILOGRAM, METER, SECOND, SECOND) * LengthAmount(2, METER))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = EnergyAmount(4, KILOGRAM, FOOT, FOOT, SECOND, SECOND),
            actual = ForceAmount(2, KILOGRAM, FOOT, SECOND, SECOND) * LengthAmount(2 * 12, INCH))
    }
}