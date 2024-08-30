package org.pcb.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.MomentumAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount

class MassAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MassAmount(4, KILOGRAM),
            actual = MassAmount(2, KILOGRAM) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MassAmount(1, KILOGRAM),
            actual = MassAmount(2, KILOGRAM) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameMass()
    {
        assertEquals(
            expected = MassAmount(1, KILOGRAM),
            actual = MassAmount(0, KILOGRAM) + MassAmount(1, KILOGRAM))
    }

    @Test
    fun plusOtherMass()
    {
        assertEquals(
            expected = MassAmount(1, POUND),
            actual = MassAmount(0, POUND) + MassAmount(16, OUNCE))
    }

    @Test
    fun minusSameMass()
    {
        assertEquals(
            expected = MassAmount(0, KILOGRAM),
            actual = MassAmount(1, KILOGRAM) - MassAmount(1, KILOGRAM))
    }

    @Test
    fun minusOtherMass()
    {
        assertEquals(
            expected = MassAmount(0, POUND),
            actual = MassAmount(1, POUND) - MassAmount(16, OUNCE))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = MassAmount(2, KILOGRAM) / MassAmount(2, KILOGRAM))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = MassAmount(2, POUND) / MassAmount(32, OUNCE))
    }

    @Test
    fun timesSpeed()
    {
        assertEquals(
            expected = MomentumAmount(4, KILOGRAM, METER, SECOND),
            actual = MassAmount(2, KILOGRAM) * SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun timesAcceleration()
    {
        assertEquals(
            expected = ForceAmount(4, KILOGRAM, METER, SECOND, SECOND),
            actual = MassAmount(2, KILOGRAM) * AccelerationAmount(2, METER, SECOND, SECOND))
    }
}