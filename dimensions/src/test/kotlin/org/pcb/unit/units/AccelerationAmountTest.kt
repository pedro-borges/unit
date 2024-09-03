package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.NaturalAmount.ONE
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
        assertEquals<AccelerationAmount<Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 1,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME2)))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<AccelerationAmount<Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 * 2,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<AccelerationAmount<Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 2 / 2,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameAcceleration()
    {
        assertEquals<AccelerationAmount<Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 0 + 1,
                                          unit = LENGTH1 / (TIME1 * TIME1)),
            actual = AccelerationAmount(amount = 0,
                                        unit = LENGTH1 / (TIME1 * TIME1)) +
                     AccelerationAmount(amount = 1,
                                        unit = LENGTH1 / (TIME1 * TIME1)))
    }

    @Test
    fun plusOtherAcceleration()
    {
        assertEquals<AccelerationAmount<Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 0 + 1,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = AccelerationAmount(amount = 0,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) +
                     AccelerationAmount(amount = 2,
                                        unit = LENGTH2 /
                                               (TIME2 * TIME2)))
    }

    @Test
    fun minusSameAcceleration()
    {
        assertEquals<AccelerationAmount<Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 1 - 1,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = AccelerationAmount(amount = 1,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) -
                     AccelerationAmount(amount = 1,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)))
    }

    @Test
    fun minusOtherAcceleration()
    {
        assertEquals<AccelerationAmount<Unit<Length>, out Unit<Time>>>(
            expected = AccelerationAmount(amount = 1 - 1,
                                          unit = LENGTH1 /
                                                 (TIME1 * TIME1)),
            actual = AccelerationAmount(amount = 1,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) -
                     AccelerationAmount(amount = 2,
                                        unit = LENGTH2 /
                                               (TIME2 * TIME2)))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) /
                     AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) /
                     AccelerationAmount(amount = 2 * 2,
                                        unit = LENGTH2 /
                                               (TIME2 * TIME2)))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals<SpeedAmount<out Unit<Length>, TIME1>>(
            expected = SpeedAmount(amount = 2 * 2,
                                   unit = LENGTH1 / TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals<SpeedAmount<out Unit<Length>, TIME1>>(
            expected = SpeedAmount(amount = 2 * 2,
                                   unit = LENGTH1 / TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) *
                     TimeAmount(amount = 1,
                                unit = TIME2))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       unit = ONE / TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) /
                     SpeedAmount(amount = 2,
                                 unit = LENGTH1 / TIME1))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 2 / 2,
                                       unit = ONE / TIME1),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) /
                     SpeedAmount(amount = 2,
                                 unit = LENGTH2 / TIME2))
    }

    @Test
    fun timesMass()
    {
        assertEquals<ForceAmount<out Unit<Mass>, out Unit<Length>, out Unit<Time>>>(
            expected = ForceAmount(amount = 2 * 2,
                                   unit = (MASS1 * LENGTH1) /
                                          (TIME1 * TIME1)),
            actual = AccelerationAmount(amount = 2,
                                        unit = LENGTH1 /
                                               (TIME1 * TIME1)) *
                     MassAmount(amount = 2,
                                unit = MASS1))
    }
}