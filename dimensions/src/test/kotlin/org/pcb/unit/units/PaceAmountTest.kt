package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class PaceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = PaceAmount(amount = 2 * 2,
                                  major = TIME1,
                                  minor = LENGTH1),
            actual = PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PaceAmount(amount = 2 / 2,
                                  major = TIME1,
                                  minor = LENGTH1),
            actual = PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSamePace()
    {
        assertEquals(
            expected = PaceAmount(amount = 0 + 1,
                                  major = TIME1,
                                  minor = LENGTH1),
            actual = PaceAmount(amount = 0,
                                major = TIME1,
                                minor = LENGTH1) +
                     PaceAmount(amount = 1,
                                major = TIME1,
                                minor = LENGTH1))
    }

    @Test
    fun plusOtherPace()
    {
        assertEquals(
            expected = PaceAmount(amount = 0 + 1,
                                  major = TIME1,
                                  minor = LENGTH1),
            actual = PaceAmount(amount = 0,
                                major = TIME1,
                                minor = LENGTH1) +
                     PaceAmount(amount = 1,
                                major = TIME2,
                                minor = LENGTH2))
    }

    @Test
    fun minusSamePace()
    {
        assertEquals(
            expected = PaceAmount(amount = 1 - 1,
                                  major = TIME1,
                                  minor = LENGTH1),
            actual = PaceAmount(amount = 1,
                                major = TIME1,
                                minor = LENGTH1) -
                     PaceAmount(amount = 1,
                                major = TIME1,
                                minor = LENGTH1))
    }

    @Test
    fun minusOtherPace()
    {
        assertEquals(
            expected = PaceAmount(amount = 1 - 1,
                                  major = TIME1,
                                  minor = LENGTH1),
            actual = PaceAmount(amount = 1,
                                major = TIME1,
                                minor = LENGTH1) -
                     PaceAmount(amount = 1,
                                major = TIME2,
                                minor = LENGTH2))
    }

    @Test
    fun divSamePace()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1) /
                     PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1))
    }

    @Test
    fun divOtherPace()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1) /
                     PaceAmount(amount = 2,
                                major = TIME2,
                                minor = LENGTH2))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1) *
                     SpeedAmount(amount = 2,
                                 major = LENGTH1,
                                 minor = TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1) *
                     SpeedAmount(amount = 2,
                                 major = LENGTH2,
                                 minor = TIME2))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  major = TIME1),
            actual = PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1) *
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  major = TIME1),
            actual = PaceAmount(amount = 2,
                                major = TIME1,
                                minor = LENGTH1) *
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }
}