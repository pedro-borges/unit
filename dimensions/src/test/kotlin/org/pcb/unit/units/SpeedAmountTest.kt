package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.AccelerationAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.MomentumAmount
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class SpeedAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 * 2,
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 / 2,
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 0 + 1,
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = SpeedAmount(amount = 0,
                                 major = LENGTH1,
                                 minor = TIME1) +
                     SpeedAmount(amount = 1,
                                 major = LENGTH1,
                                 minor = TIME1))
    }

    @Test
    fun plusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 0 + (2 / 2),
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = SpeedAmount(amount = 0,
                                 major = LENGTH1,
                                 minor = TIME1) +
                     SpeedAmount(amount = 1,
                                 major = LENGTH2,
                                 minor = TIME2))
    }

    @Test
    fun minusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1 - 1,
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = SpeedAmount(amount = 1,
                                 major = LENGTH1,
                                 minor = TIME1) -
                     SpeedAmount(amount = 1,
                                 major = LENGTH1,
                                 minor = TIME1))
    }

    @Test
    fun minusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1 - (2 / 2),
                                   major = LENGTH1,
                                   minor = TIME1),
            actual = SpeedAmount(amount = 1,
                                 major = LENGTH1,
                                 minor = TIME1) -
                     SpeedAmount(amount = 1,
                                 major = LENGTH2,
                                 minor = TIME2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) /
                     SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) /
                     SpeedAmount(amount = 2,
                                 major = LENGTH2,
                                 minor = TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 * 2,
                                    major = LENGTH1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) *
                     TimeAmount(amount = 2,
                                major = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 * 2,
                                    major = LENGTH1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) *
                     TimeAmount(amount = 1,
                                major = TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) /
                     TimeAmount(amount = 2,
                                major = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) /
                     TimeAmount(amount = 1,
                                major = TIME2))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       minor = TIME1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) /
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       minor = TIME1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) /
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun timesSameMomentum()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) *
                     MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH1,
                                    minor = TIME1))
    }

    @Test
    fun timesOtherMomentum()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1) *
                     MomentumAmount(amount = 2,
                                    major = MASS1 * LENGTH2,
                                    minor = TIME2))
    }
}
