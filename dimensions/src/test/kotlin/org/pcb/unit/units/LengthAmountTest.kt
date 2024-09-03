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
                                    major = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 / 2,
                                    major = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 0 + 1,
                                    major = LENGTH1),
            actual = LengthAmount(amount = 0,
                                  major = LENGTH1) +
                     LengthAmount(amount = 1,
                                  major = LENGTH1))
    }

    @Test
    fun plusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 0 + 2,
                                    major = LENGTH1),
            actual = LengthAmount(amount = 0,
                                  major = LENGTH1) +
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun minusSameLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 - 2,
                                    major = LENGTH1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) -
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun minusOtherLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 - 2, LENGTH1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) -
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) /
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) /
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = SpeedAmount(amount = 2 / 2,
                                   pair = LENGTH1 to TIME1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) /
                     TimeAmount(amount = 2,
                                time = TIME1))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 2 * 2,
                                  major = LENGTH1 * LENGTH1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) *
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 2 * 2,
                                  major = LENGTH1 * LENGTH1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) *
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun timesSameArea()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) *
                     AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1))
    }

    @Test
    fun timesOtherArea()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2 * 2,
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = LengthAmount(amount = 2,
                                  major = LENGTH1) *
                     AreaAmount(amount = 1,
                                major = LENGTH2 * LENGTH2))
    }
}
