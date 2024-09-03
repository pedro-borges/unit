package org.pcb.unit

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
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
            expected = MassAmount(amount = 2 * 2,
                                  major = MASS1),
            actual = MassAmount(amount = 2,
                                major = MASS1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  major = MASS1),
            actual = MassAmount(amount = 2,
                                major = MASS1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameMass()
    {
        assertEquals(
            expected = MassAmount(amount = 0 + 1,
                                  major = MASS1),
            actual = MassAmount(amount = 0,
                                major = MASS1) +
                     MassAmount(amount = 1,
                                major = MASS1))
    }

    @Test
    fun plusOtherMass()
    {
        assertEquals(
            expected = MassAmount(amount = 0 + 2,
                                  major = MASS1),
            actual = MassAmount(amount = 0,
                                major = MASS1) +
                     MassAmount(amount = 1,
                                major = MASS2))
    }

    @Test
    fun minusSameMass()
    {
        assertEquals(
            expected = MassAmount(amount = 1 - 1,
                                  major = MASS1),
            actual = MassAmount(amount = 1,
                                major = MASS1) -
                     MassAmount(amount = 1,
                                major = MASS1))
    }

    @Test
    fun minusOtherMass()
    {
        assertEquals(
            expected = MassAmount(amount = 2 - 2,
                                  major = MASS1),
            actual = MassAmount(amount = 2,
                                major = MASS1) -
                     MassAmount(amount = 1,
                                major = MASS2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = MassAmount(amount = 2,
                                major = MASS1) /
                     MassAmount(amount = 2,
                                major = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = MassAmount(amount = 2,
                                major = MASS1) /
                     MassAmount(amount = 1,
                                major = MASS2))
    }

    @Test
    fun timesSpeed()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 * 2,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = MassAmount(amount = 2,
                                major = MASS1) *
                     SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1))
    }

    @Test
    fun timesAcceleration()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 * 2,
                                   major = MASS1 * LENGTH1,
                                   minor = TIME1 * TIME1),
            actual = MassAmount(amount = 2,
                                major = MASS1) *
                     AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1))
    }
}