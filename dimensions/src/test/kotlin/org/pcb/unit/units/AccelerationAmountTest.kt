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
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class AccelerationAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 1,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 * 2,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameAcceleration()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 0 + 1,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = AccelerationAmount(amount = 0,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) +
                     AccelerationAmount(amount = 1,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1))
    }

    @Test
    fun plusOtherAcceleration()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 0 + 1,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = AccelerationAmount(amount = 0,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) +
                     AccelerationAmount(amount = 2,
                                        major = LENGTH2,
                                        minor = TIME2 * TIME2))
    }

    @Test
    fun minusSameAcceleration()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 1 - 1,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = AccelerationAmount(amount = 1,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) -
                     AccelerationAmount(amount = 1,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1))
    }

    @Test
    fun minusOtherAcceleration()
    {
        assertEquals<AccelerationAmount<LENGTH1, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 1 - 1,
                                          major = LENGTH1,
                                          minor = TIME1 * TIME1),
            actual = AccelerationAmount(amount = 1,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) -
                     AccelerationAmount(amount = 2,
                                        major = LENGTH2,
                                        minor = TIME2 * TIME2))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) /
                     AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) /
                     AccelerationAmount(amount = 2 * 2,
                                        major = LENGTH2,
                                        minor = TIME2 * TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = SpeedAmount(2 * 2, LENGTH1 to TIME1),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) *
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = SpeedAmount(2 * 2, LENGTH1 to TIME1),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) *
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       minor = TIME1),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) /
                     SpeedAmount(2, LENGTH1, TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       minor = TIME1),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) /
                     SpeedAmount(2, LENGTH2, TIME2))
    }

    @Test
    fun timesMass()
    {
        assertEquals<ForceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 * 2,
                                   major = MASS1 * LENGTH1,
                                   minor = TIME1 * TIME1),
            actual = AccelerationAmount(amount = 2,
                                        major = LENGTH1,
                                        minor = TIME1 * TIME1) *
                     MassAmount(amount = 2,
                                major = MASS1))
    }
}