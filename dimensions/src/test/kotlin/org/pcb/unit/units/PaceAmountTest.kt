package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
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
                                  unit = TIME1 to LENGTH1),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 to LENGTH1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PaceAmount(amount = 1,
                                  unit = TIME1 to LENGTH1),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 to LENGTH1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSamePace()
    {
        assertEquals(
            expected = PaceAmount(amount = 1,
                                  unit = TIME1 to LENGTH1),
            actual = PaceAmount(amount = 0,
                                unit = TIME1 to LENGTH1) +
                     PaceAmount(amount = 1,
                                unit = TIME1 to LENGTH1))
    }

    @Test
    fun plusOtherPace()
    {
        assertEquals(
            expected = PaceAmount(amount = 1,
                                  unit = TIME1 to LENGTH1),
            actual = PaceAmount(amount = 0,
                                unit = TIME1 to LENGTH1) +
                     PaceAmount(amount = 1,
                                unit = TIME2 to LENGTH2))
    }

    @Test
    fun minusSamePace()
    {
        assertEquals(
            expected = PaceAmount(amount = 0,
                                  unit = TIME1 to LENGTH1),
            actual = PaceAmount(amount = 1,
                                unit = TIME1 to LENGTH1) -
                     PaceAmount(amount = 1,
                                unit = TIME1 to LENGTH1))
    }

    @Test
    fun minusOtherPace()
    {
        assertEquals(
            expected = PaceAmount(amount = 0,
                                  unit = TIME1 to LENGTH1),
            actual = PaceAmount(amount = 1,
                                unit = TIME1 to LENGTH1) -
                     PaceAmount(amount = 1,
                                unit = TIME2 to LENGTH2))
    }

    @Test
    fun divSamePace()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 to LENGTH1) /
                     PaceAmount(amount = 2,
                                unit = TIME1 to LENGTH1))
    }

    @Test
    fun divOtherPace()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 to LENGTH1) /
                     PaceAmount(amount = 2,
                                unit = TIME2 to LENGTH2))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 to LENGTH1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 to LENGTH1) *
                     SpeedAmount(amount = 2,
                                 unit = LENGTH2 to TIME2))
    }

    @Test
    fun timesSameSpace()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = PaceAmount(amount = 2,
                                unit = TIME1 to LENGTH1) *
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
                                unit = TIME1 to LENGTH2) *
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }
}