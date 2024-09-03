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
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 * 2,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 / 2,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameMomentum()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 0 + 1,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = MomentumAmount(amount = 0,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) +
                     MomentumAmount(amount = 1,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1))
    }

    @Test
    fun plusOtherMomentum()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 0 + 2,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = MomentumAmount(amount = 0,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) +
                     MomentumAmount(amount = 1,
                                    major = MASS2 * LENGTH2,
                                    minor = TIME2))
    }

    @Test
    fun minusSameMomentum()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 1 - 1,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = MomentumAmount(amount = 1,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) -
                     MomentumAmount(amount = 1,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1))
    }

    @Test
    fun minusOtherMomentum()
    {
        assertEquals<MomentumAmount<out Unit<Mass>, LENGTH1, TIME1>>(
            expected = MomentumAmount(amount = 2 - 2,
                                      major = MASS1 * LENGTH1,
                                      minor = TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) -
                     MomentumAmount(amount = 1,
                                    major = MASS2 * LENGTH2,
                                    minor = TIME2))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     MomentumAmount(amount = 1,
                                    major = MASS2 * LENGTH2,
                                    minor = TIME2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 / 2,
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     MassAmount(amount = 2,
                                major = MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 / 2,
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     MassAmount(amount = 1,
                                major = MASS2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  major = MASS1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = MassAmount(amount = 2 / 2,
                                  major = MASS1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     SpeedAmount(amount = 2,
                                 major = LENGTH2,
                                 minor = TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 / 2,
                                   major = MASS1 * LENGTH1,
                                   minor = TIME1 * TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     TimeAmount(amount = 2,
                                major = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 1,
                                   major = MASS1 * LENGTH1,
                                   minor = TIME1 * TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) /
                     TimeAmount(amount = 1,
                                major = TIME2))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) *
                     SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1) *
                     SpeedAmount(amount = 2,
                                 major = LENGTH2,
                                 minor = TIME2))
    }
}