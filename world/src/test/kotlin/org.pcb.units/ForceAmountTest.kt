package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.EnergyAmount
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.MomentumAmount
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.mass.Gram.GRAM
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class ForceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ForceAmount(4, GRAM, METER, SECOND, SECOND),
            actual = ForceAmount(2, GRAM, METER, SECOND, SECOND) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ForceAmount(1, GRAM, METER, SECOND, SECOND),
            actual = ForceAmount(2, GRAM, METER, SECOND, SECOND) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameForce()
    {
        assertEquals(
            expected = ForceAmount(1, GRAM, METER, SECOND, SECOND),
            actual = ForceAmount(0, GRAM, METER, SECOND, SECOND) + ForceAmount(1, GRAM, METER, SECOND, SECOND))
    }

    @Test
    fun plusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(1, POUND, FOOT, SECOND, SECOND),
            actual = ForceAmount(0, POUND, FOOT, SECOND, SECOND) + ForceAmount(16 * 12 * 60 * 60, OUNCE, INCH, MINUTE, MINUTE))
    }

    @Test
    fun minusSameForce()
    {
        assertEquals(
            expected = ForceAmount(0, GRAM, METER, SECOND, SECOND),
            actual = ForceAmount(1, GRAM, METER, SECOND, SECOND) - ForceAmount(1, GRAM, METER, SECOND, SECOND))
    }

    @Test
    fun minusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(0, POUND, FOOT, SECOND, SECOND),
            actual = ForceAmount(1, POUND, FOOT, SECOND, SECOND) - ForceAmount(16 * 12 * 60 * 60, OUNCE, INCH, MINUTE, MINUTE))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ForceAmount(2, GRAM, METER, SECOND, SECOND) / ForceAmount(2, GRAM, METER, SECOND, SECOND))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ForceAmount(2, POUND, FOOT, SECOND, SECOND) / ForceAmount(2 * 16 * 12 * 60 * 60, OUNCE, INCH, MINUTE, MINUTE))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND, SECOND),
            actual = ForceAmount(2, GRAM, METER, SECOND, SECOND) / MassAmount(2, GRAM))
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
            expected = MassAmount(1, GRAM),
            actual = ForceAmount(2, GRAM, METER, SECOND, SECOND) / AccelerationAmount(2, METER, SECOND, SECOND))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = MassAmount(1, GRAM),
            actual = ForceAmount(2, GRAM, FOOT, SECOND, SECOND) / AccelerationAmount(2 * 12 * 60 * 60, INCH, MINUTE, MINUTE))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MomentumAmount(4, GRAM, METER, SECOND),
            actual = ForceAmount(2, GRAM, METER, SECOND, SECOND) * TimeAmount(2, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MomentumAmount(4, GRAM, FOOT, MINUTE),
            actual = ForceAmount(2, GRAM, FOOT, MINUTE, MINUTE) * TimeAmount(2 * 60, SECOND))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = EnergyAmount(4, GRAM, METER, METER, SECOND, SECOND),
            actual = ForceAmount(2, GRAM, METER, SECOND, SECOND) * SpaceAmount(2, METER))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = EnergyAmount(4, GRAM, FOOT, FOOT, SECOND, SECOND),
            actual = ForceAmount(2, GRAM, FOOT, SECOND, SECOND) * SpaceAmount(2 * 12, INCH))
    }
}