package org.pcb.unit

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.units.AccelerationAmount
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.MomentumAmount
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1

class MassAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MassAmount(2 * 2, MASS1),
            actual = MassAmount(2, MASS1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MassAmount(2 / 2, MASS1),
            actual = MassAmount(2, MASS1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameMass()
    {
        assertEquals(
            expected = MassAmount(0 + 1, MASS1),
            actual = MassAmount(0, MASS1) +
                     MassAmount(1, MASS1))
    }

    @Test
    fun plusOtherMass()
    {
        assertEquals(
            expected = MassAmount(0 + 2, MASS1),
            actual = MassAmount(0, MASS1) +
                     MassAmount(1, MASS2))
    }

    @Test
    fun minusSameMass()
    {
        assertEquals(
            expected = MassAmount(1 - 1, MASS1),
            actual = MassAmount(1, MASS1) -
                     MassAmount(1, MASS1))
    }

    @Test
    fun minusOtherMass()
    {
        assertEquals(
            expected = MassAmount(2 - 2, MASS1),
            actual = MassAmount(2, MASS1) -
                     MassAmount(1, MASS2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = MassAmount(2, MASS1) /
                     MassAmount(2, MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = MassAmount(2, MASS1) /
                     MassAmount(1, MASS2))
    }

    @Test
    fun timesSpeed()
    {
        assertEquals(
            expected = MomentumAmount(2 * 2, MASS1, LENGTH1, TIME1),
            actual = MassAmount(2, MASS1) *
                     SpeedAmount(2, LENGTH1, TIME1))
    }

    @Test
    fun timesAcceleration()
    {
        assertEquals(
            expected = ForceAmount(2 * 2, MASS1, LENGTH1, TIME1, TIME1),
            actual = MassAmount(2, MASS1) *
                     AccelerationAmount(2, LENGTH1, TIME1, TIME1))
    }
}