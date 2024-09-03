package org.pcb.unit

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.dimension.Length
import org.pcb.unit.units.AreaAmount
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.TIME1

class LengthAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 * 2,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 / 2,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 0 + 1,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 0,
                                  unit = LENGTH1) +
                     LengthAmount(amount = 1,
                                  unit = LENGTH1))
    }

    @Test
    fun plusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 0 + 2,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 0,
                                  unit = LENGTH1) +
                     LengthAmount(amount = 1,
                                  unit = LENGTH2))
    }

    @Test
    fun minusSameLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 - 2,
                                    unit = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) -
                     LengthAmount(amount = 1,
                                  unit = LENGTH2))
    }

    @Test
    fun minusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 - 2, LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) -
                     LengthAmount(amount = 1,
                                  unit = LENGTH2))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) /
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) /
                     LengthAmount(amount = 1,
                                  unit = LENGTH2))
    }

    @Test
    fun divTime()
    {
        assertEquals<SpeedAmount<out Unit<Length>, TIME1>>(
            expected = SpeedAmount(amount = 2 / 2,
                                   unit = LENGTH1 / TIME1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = LENGTH1 * LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = LENGTH1 * LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     LengthAmount(amount = 1,
                                  unit = LENGTH2))
    }

    @Test
    fun timesSameArea()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = LENGTH1 * LENGTH1 * LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     AreaAmount(amount = 2,
                                unit = LENGTH1 * LENGTH1))
    }

    @Test
    fun timesOtherArea()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 2 * 2 * 2,
                                    unit = LENGTH1 * LENGTH1 * LENGTH1),
            actual = LengthAmount(amount = 2,
                                  unit = LENGTH1) *
                     AreaAmount(amount = 1,
                                unit = LENGTH2 * LENGTH2))
    }
}
