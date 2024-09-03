package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class MomentumAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MomentumAmount(2 * 2, (MASS1 * LENGTH1) to TIME1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MomentumAmount(2 / 2, (MASS1 * LENGTH1) to TIME1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(0 + 1, (MASS1 * LENGTH1) to TIME1),
            actual = MomentumAmount(0, (MASS1 * LENGTH1) to TIME1) +
                     MomentumAmount(1, (MASS1 * LENGTH1) to TIME1))
    }

    @Test
    fun plusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(0 + 2, (MASS1 * LENGTH1) to TIME1),
            actual = MomentumAmount(0, (MASS1 * LENGTH1) to TIME1) +
                     MomentumAmount(1, (MASS2 * LENGTH2) to TIME2))
    }

    @Test
    fun minusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(1 - 1, (MASS1 * LENGTH1) to TIME1),
            actual = MomentumAmount(1, (MASS1 * LENGTH1) to TIME1) -
                     MomentumAmount(1, (MASS1 * LENGTH1) to TIME1))
    }

    @Test
    fun minusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(2 - 2, (MASS1 * LENGTH1) to TIME1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) -
                     MomentumAmount(1, (MASS2 * LENGTH2) to TIME2))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) /
                     MomentumAmount(2, (MASS1 * LENGTH1) to TIME1))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) /
                     MomentumAmount(1, (MASS2 * LENGTH2) to TIME2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1 to TIME1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) /
                     MassAmount(amount = 2,
                                major = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1 to TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     MassAmount(amount = 1,
                                major = MASS2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  major = MASS1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) /
                     SpeedAmount(2, LENGTH1 to TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  major = MASS1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) /
                     SpeedAmount(2, LENGTH2 to TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 / 2,
                                   major = MASS1 * LENGTH1,
                                   minor = TIME1 * TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     TimeAmount(2, TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 1,
                                   major = MASS1 * LENGTH1,
                                   minor = TIME1 * TIME1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) /
                     TimeAmount(1, TIME2))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) *
                     SpeedAmount(2, LENGTH1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = MomentumAmount(2, (MASS1 * LENGTH1) to TIME1) *
                     SpeedAmount(2, LENGTH2 to TIME2))
    }
}