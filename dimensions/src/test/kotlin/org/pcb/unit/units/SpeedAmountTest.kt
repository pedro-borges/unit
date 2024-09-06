package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.AccelerationAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.MomentumAmount
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
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
                                   unit = LENGTH1 to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = LENGTH1 to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1,
                                   unit = LENGTH1 to TIME1),
            actual = SpeedAmount(amount = 0,
                                 unit = LENGTH1 to TIME1) +
                     SpeedAmount(amount = 1,
                                 unit = LENGTH1 to TIME1))
    }

    @Test
    fun plusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 0 + (2 / 2),
                                   unit = LENGTH1 to TIME1),
            actual = SpeedAmount(amount = 0,
                                 unit = LENGTH1 to TIME1) +
                     SpeedAmount(amount = 1,
                                 unit = LENGTH2 to TIME2))
    }

    @Test
    fun minusSameSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 0,
                                   unit = LENGTH1 to TIME1),
            actual = SpeedAmount(amount = 1,
                                 unit = LENGTH1 to TIME1) -
                     SpeedAmount(amount = 1,
                                 unit = LENGTH1 to TIME1))
    }

    @Test
    fun minusOtherSpeed()
    {
        assertEquals(
            expected = SpeedAmount(amount = 1 - (2 / 2),
                                   unit = LENGTH1 to TIME1),
            actual = SpeedAmount(amount = 1,
                                 unit = LENGTH1 to TIME1) -
                     SpeedAmount(amount = 1,
                                 unit = LENGTH2 to TIME2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) /
                     SpeedAmount(amount = 2,
                                 unit = LENGTH2 to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 * 2,
                                    unit = LENGTH1),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 * 2,
                                    unit = LENGTH1),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME2) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = LENGTH1 to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = AccelerationAmount(amount = 1,
                                          unit = LENGTH2 to TIME2),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH2 to TIME2) /
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) /
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME2),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH2 to TIME2) /
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }

    @Test
    fun timesSameMomentum()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) *
                     MomentumAmount(amount = 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1))
    }

    @Test
    fun timesOtherMomentum()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, LENGTH1) to TIME1),
            actual = SpeedAmount(amount = 2,
                                 unit = LENGTH1 to TIME1) *
                     MomentumAmount(amount = 2,
                                    unit = U2(MASS1, LENGTH2) to TIME2))
    }
}
