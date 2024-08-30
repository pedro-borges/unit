package org.pcb.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.MomentumAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.mass.Gram.GRAM
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Second.SECOND

class MassAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MassAmount(4, GRAM),
            actual = MassAmount(2, GRAM) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MassAmount(1, GRAM),
            actual = MassAmount(2, GRAM) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameMass()
    {
        assertEquals(
            expected = MassAmount(1, GRAM),
            actual = MassAmount(0, GRAM) + MassAmount(1, GRAM))
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
            expected = MassAmount(0, GRAM),
            actual = MassAmount(1, GRAM) - MassAmount(1, GRAM))
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
            actual = MassAmount(2, GRAM) / MassAmount(2, GRAM))
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
            expected = MomentumAmount(4, GRAM, METER, SECOND),
            actual = MassAmount(2, GRAM) * SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun timesAcceleration()
    {
        assertEquals(
            expected = ForceAmount(4, GRAM, METER, SECOND, SECOND),
            actual = MassAmount(2, GRAM) * AccelerationAmount(2, METER, SECOND, SECOND))
    }
}