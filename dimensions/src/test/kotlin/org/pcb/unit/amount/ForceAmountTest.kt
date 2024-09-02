package org.pcb.unit.amount

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.test.LENGTH1
import org.pcb.unit.amount.test.LENGTH2
import org.pcb.unit.amount.test.MASS1
import org.pcb.unit.amount.test.MASS2
import org.pcb.unit.amount.test.TIME1
import org.pcb.unit.amount.test.TIME2
import org.pcb.unit.dimension.Time

class ForceAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ForceAmount<MASS1, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(1, MASS1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ForceAmount(2 * 2, MASS1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ForceAmount(2 / 2, MASS1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameForce()
    {
        assertEquals(
            expected = ForceAmount(0 + 1, MASS1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(0, MASS1, LENGTH1, TIME1, TIME1) +
                     ForceAmount(1, MASS1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun plusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(0 + 1, MASS1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(0, MASS1, LENGTH1, TIME1, TIME1) +
                     ForceAmount(1, MASS2, LENGTH2, TIME2, TIME2))
    }

    @Test
    fun minusSameForce()
    {
        assertEquals(
            expected = ForceAmount(1 - 1, MASS1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(1, MASS1, LENGTH1, TIME1, TIME1) -
                     ForceAmount(1, MASS1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun minusOtherForce()
    {
        assertEquals(
            expected = ForceAmount(1 - 1, MASS1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(1, MASS1, LENGTH1, TIME1, TIME1) -
                     ForceAmount(1, MASS2, LENGTH2, TIME2, TIME2))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) /
                     ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) /
                     ForceAmount(2, MASS2, LENGTH2, TIME2, TIME2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = AccelerationAmount(2 / 2, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) /
                     MassAmount(2, MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = AccelerationAmount(2 / 2, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) /
                     MassAmount(1, MASS2))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = MassAmount(2 / 2, MASS1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) /
                     AccelerationAmount(2, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = MassAmount(2 / 2, MASS1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) /
                     AccelerationAmount(2 * 2, LENGTH2, TIME2, TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MomentumAmount(2 * 2, MASS1, LENGTH1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) *
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MomentumAmount(2 * 2, MASS1, LENGTH1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) *
                     TimeAmount(1, TIME2))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = EnergyAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) *
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = EnergyAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1) *
                     LengthAmount(1, LENGTH2))
    }
}