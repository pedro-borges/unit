package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
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
            expected = SpeedAmount(2 * 2, LENGTH1, TIME1),
            actual = SpeedAmount(2, LENGTH1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1, TIME1),
            actual = SpeedAmount(2, LENGTH1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(0 + 1, LENGTH1, TIME1),
            actual = SpeedAmount(0, LENGTH1, TIME1) +
                     SpeedAmount(1, LENGTH1, TIME1))
    }

    @Test
    fun plusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(0 + (2 / 2), LENGTH1, TIME1),
            actual = SpeedAmount(0, LENGTH1, TIME1) +
                     SpeedAmount(1, LENGTH2, TIME2))
    }

    @Test
    fun minusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(1 - 1, LENGTH1, TIME1),
            actual = SpeedAmount(1, LENGTH1, TIME1) -
                     SpeedAmount(1, LENGTH1, TIME1))
    }

    @Test
    fun minusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(1 - (2 / 2), LENGTH1, TIME1),
            actual = SpeedAmount(1, LENGTH1, TIME1) -
                     SpeedAmount(1, LENGTH2, TIME2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = SpeedAmount(2, LENGTH1, TIME1) /
                     SpeedAmount(2, LENGTH1, TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = SpeedAmount(2, LENGTH1, TIME1) /
                     SpeedAmount(2, LENGTH2, TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = LengthAmount(2 * 2, LENGTH1),
            actual = SpeedAmount(2, LENGTH1, TIME1) *
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = LengthAmount(2 * 2, LENGTH1),
            actual = SpeedAmount(2, LENGTH1, TIME1) *
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = AccelerationAmount(2 / 2, LENGTH1, TIME1, TIME1),
            actual = SpeedAmount(2, LENGTH1, TIME1) /
                     TimeAmount(2, TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = AccelerationAmount(2 / 2, LENGTH1, TIME1, TIME1),
            actual = SpeedAmount(2, LENGTH1, TIME1) /
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = FrequencyAmount(2 / 2, TIME1),
            actual = SpeedAmount(2, LENGTH1, TIME1) /
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = FrequencyAmount(2 / 2, TIME1),
            actual = SpeedAmount(2, LENGTH1, TIME1) /
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun timesSameMomentum()
    {
        assertEquals(
            expected = EnergyAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = SpeedAmount(2, LENGTH1, TIME1) *
                     MomentumAmount(2, MASS1, LENGTH1, TIME1))
    }

    @Test
    fun timesOtherMomentum()
    {
        assertEquals(
            expected = EnergyAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = SpeedAmount(2, LENGTH1, TIME1) *
                     MomentumAmount(2, MASS1, LENGTH2, TIME2))
    }
}
