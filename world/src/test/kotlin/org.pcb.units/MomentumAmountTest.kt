package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.MomentumAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.length.Foot.FOOT
import org.pcb.units.amount.length.Inch.INCH
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Minute.MINUTE

class MomentumAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MomentumAmount(4, KILOGRAM, METER, SECOND),
            actual = MomentumAmount(2, KILOGRAM, METER, SECOND) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MomentumAmount(1, KILOGRAM, METER, SECOND),
            actual = MomentumAmount(2, KILOGRAM, METER, SECOND) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(1, KILOGRAM, METER, SECOND),
            actual = MomentumAmount(0, KILOGRAM, METER, SECOND) +
                     MomentumAmount(1, KILOGRAM, METER, SECOND))
    }

    @Test
    fun plusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(1, POUND, FOOT, SECOND),
            actual = MomentumAmount(0, POUND, FOOT, SECOND) +
                     MomentumAmount(16 * 12 * 60, OUNCE, INCH, MINUTE))
    }

    @Test
    fun minusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(0, KILOGRAM, METER, SECOND),
            actual = MomentumAmount(1, KILOGRAM, METER, SECOND) -
                     MomentumAmount(1, KILOGRAM, METER, SECOND))
    }

    @Test
    fun minusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(0, POUND, FOOT, SECOND),
            actual = MomentumAmount(1, POUND, FOOT, SECOND) -
                     MomentumAmount(16 * 12 * 60, OUNCE, INCH, MINUTE))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = MomentumAmount(2, KILOGRAM, METER, SECOND) /
                     MomentumAmount(2, KILOGRAM, METER, SECOND))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = MomentumAmount(2, POUND, FOOT, SECOND) /
                     MomentumAmount(2 * 16 * 12 * 60, OUNCE, INCH, MINUTE))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = MomentumAmount(2, KILOGRAM, METER, SECOND) /
                     MassAmount(2, KILOGRAM))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = MomentumAmount(2, POUND, METER, SECOND) /
                     MassAmount(2 * 16, OUNCE))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = MassAmount(1, KILOGRAM),
            actual = MomentumAmount(2, KILOGRAM, METER, SECOND) /
                     SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = MassAmount(1, KILOGRAM),
            actual = MomentumAmount(2, KILOGRAM, FOOT, SECOND) /
                     SpeedAmount(2 * 12 * 60, INCH, MINUTE))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ForceAmount(1, KILOGRAM, METER, SECOND, SECOND),
            actual = MomentumAmount(2, KILOGRAM, METER, SECOND) /
                     TimeAmount(2, SECOND))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ForceAmount(1, KILOGRAM, METER, MINUTE, MINUTE),
            actual = MomentumAmount(2, KILOGRAM, METER, MINUTE) /
                     TimeAmount(2 * 60, SECOND))
    }
}