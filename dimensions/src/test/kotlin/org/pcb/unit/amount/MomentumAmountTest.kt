package org.pcb.unit.amount

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.test.LENGTH1
import org.pcb.unit.amount.test.LENGTH2
import org.pcb.unit.amount.test.MASS1
import org.pcb.unit.amount.test.MASS2
import org.pcb.unit.amount.test.TIME1
import org.pcb.unit.amount.test.TIME2

class MomentumAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = MomentumAmount(2 * 2, MASS1, LENGTH1, TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = MomentumAmount(2 / 2, MASS1, LENGTH1, TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(0 + 1, MASS1, LENGTH1, TIME1),
            actual = MomentumAmount(0, MASS1, LENGTH1, TIME1) +
                     MomentumAmount(1, MASS1, LENGTH1, TIME1))
    }

    @Test
    fun plusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(0 + 2, MASS1, LENGTH1, TIME1),
            actual = MomentumAmount(0, MASS1, LENGTH1, TIME1) +
                     MomentumAmount(1, MASS2, LENGTH2, TIME2))
    }

    @Test
    fun minusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(1 - 1, MASS1, LENGTH1, TIME1),
            actual = MomentumAmount(1, MASS1, LENGTH1, TIME1) -
                     MomentumAmount(1, MASS1, LENGTH1, TIME1))
    }

    @Test
    fun minusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(2 - 2, MASS1, LENGTH1, TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) -
                     MomentumAmount(1, MASS2, LENGTH2, TIME2))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     MomentumAmount(2, MASS1, LENGTH1, TIME1))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     MomentumAmount(1, MASS2, LENGTH2, TIME2))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1, TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     MassAmount(2, MASS1))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = SpeedAmount(2 / 2, LENGTH1, TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     MassAmount(1, MASS2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = MassAmount(2 / 2, MASS1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     SpeedAmount(2, LENGTH1, TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = MassAmount(2 / 2, MASS1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     SpeedAmount(2, LENGTH2, TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ForceAmount(2 / 2, MASS1, LENGTH1, TIME1, TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     TimeAmount(2, TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ForceAmount(1, MASS1, LENGTH1, TIME1, TIME1),
            actual = MomentumAmount(2, MASS1, LENGTH1, TIME1) /
                     TimeAmount(1, TIME2))
    }
}