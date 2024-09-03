package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Time
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
        assertEquals<PaceAmount<out Unit<Time>, LENGTH1>>(
            expected = PaceAmount(amount = 2 * 2,
                                  unit = TIME1 / LENGTH1),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<PaceAmount<out Unit<Time>, LENGTH1>>(
            expected = PaceAmount(amount = 2 / 2,
                                  unit = TIME1 / LENGTH1),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSamePace()
    {
        assertEquals<PaceAmount<out Unit<Time>, LENGTH1>>(
            expected = PaceAmount(amount = 0 + 1,
                                  unit = TIME1 / LENGTH1),
            actual = PaceAmount(amount = 0,
                                unit = TIME1 / LENGTH1) +
                     PaceAmount(amount = 1,
                                unit = TIME1 / LENGTH1))
    }

    @Test
    fun plusOtherPace()
    {
        assertEquals<PaceAmount<out Unit<Time>, LENGTH1>>(
            expected = PaceAmount(amount = 0 + 1,
                                  unit = TIME1 / LENGTH1),
            actual = PaceAmount(amount = 0,
                                unit = TIME1 / LENGTH1) +
                     PaceAmount(amount = 1,
                                unit = TIME2 / LENGTH2))
    }

    @Test
    fun minusSamePace()
    {
        assertEquals<PaceAmount<out Unit<Time>, LENGTH1>>(
            expected = PaceAmount(amount = 1 - 1,
                                  unit = TIME1 / LENGTH1),
            actual = PaceAmount(amount = 1,
                                unit = TIME1 / LENGTH1) -
                     PaceAmount(amount = 1,
                                unit = TIME1 / LENGTH1))
    }

    @Test
    fun minusOtherPace()
    {
        assertEquals<PaceAmount<out Unit<Time>, LENGTH1>>(
            expected = PaceAmount(amount = 1 - 1,
                                  unit = TIME1 / LENGTH1),
            actual = PaceAmount(amount = 1,
                                unit = TIME1 / LENGTH1) -
                     PaceAmount(amount = 1,
                                unit = TIME2 / LENGTH2))
    }

    @Test
    fun divSamePace()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1) /
                     PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1))
    }

    @Test
    fun divOtherPace()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1) /
                     PaceAmount(amount = 2,
                                unit = TIME2 / LENGTH2))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 / TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH2 / TIME2))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesOtherSpace()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 / LENGTH1) *
                     LengthAmount(amount = 1,
                                  unit = LENGTH2))
    }
}