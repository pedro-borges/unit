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
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class ForceAmountTest
{
    @Test
    fun plusSameForce()
    {
        assertEquals(
            expected = ForceAmount(1, GRAM, METER, SECOND),
            actual = ForceAmount(0, GRAM, METER, SECOND) + ForceAmount(1, GRAM, METER, SECOND))
    }

    @Test
    fun plusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(1, POUND, FOOT, SECOND),
            actual = ForceAmount(0, POUND, FOOT, SECOND) + ForceAmount(691200, OUNCE, INCH, MINUTE))
    }

    @Test
    fun minusSameForce()
    {
        assertEquals(
            expected = ForceAmount(0, GRAM, METER, SECOND),
            actual = ForceAmount(1, GRAM, METER, SECOND) - ForceAmount(1, GRAM, METER, SECOND))
    }

    @Test
    fun minusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(0, POUND, FOOT, SECOND),
            actual = ForceAmount(1, POUND, FOOT, SECOND) - ForceAmount(691200, OUNCE, INCH, MINUTE))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ForceAmount(2, GRAM, METER, SECOND) / ForceAmount(2, GRAM, METER, SECOND))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ForceAmount(2, POUND, FOOT, SECOND) / ForceAmount(1382400, OUNCE, INCH, MINUTE))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND),
            actual = ForceAmount(2, GRAM, METER, SECOND) / MassAmount(2, GRAM))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND),
            actual = ForceAmount(2, POUND, METER, SECOND) / MassAmount(32, OUNCE))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = MassAmount(1, GRAM),
            actual = ForceAmount(2, GRAM, METER, SECOND) / AccelerationAmount(2, METER, SECOND))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = MassAmount(1, GRAM),
            actual = ForceAmount(2, GRAM, FOOT, SECOND) / AccelerationAmount(86400, INCH, MINUTE))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MomentumAmount(4, GRAM, METER, SECOND),
            actual = ForceAmount(2, GRAM, METER, SECOND) * TimeAmount(2, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MomentumAmount(4, GRAM, FOOT, MINUTE),
            actual = ForceAmount(2, GRAM, FOOT, MINUTE) * TimeAmount(120, SECOND))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = EnergyAmount(4, GRAM, METER, SECOND),
            actual = ForceAmount(2, GRAM, METER, SECOND) * SpaceAmount(2, METER))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = EnergyAmount(4, GRAM, FOOT, SECOND),
            actual = ForceAmount(2, GRAM, FOOT, SECOND) * SpaceAmount(24, INCH))
    }
}