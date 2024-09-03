package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2

class AreaAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 2 * 2,
                                  major = LENGTH1 * LENGTH1),
            actual = AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 2 / 2,
                                  major = LENGTH1 * LENGTH1),
            actual = AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameArea()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 0 + 1,
                                  major = LENGTH1 * LENGTH1),
            actual = AreaAmount(amount = 0,
                                major = LENGTH1 * LENGTH1) +
                     AreaAmount(amount = 1,
                                major = LENGTH1 * LENGTH1))
    }

    @Test
    fun plusOtherArea()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 0 + (2 * 2),
                                  major = LENGTH1 * LENGTH1),
            actual = AreaAmount(amount = 0,
                                major = LENGTH1 * LENGTH1) +
                     AreaAmount(amount = 1,
                                major = LENGTH2 * LENGTH2))
    }

    @Test
    fun minusSameArea()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 1 - 1,
                                  major = LENGTH1 * LENGTH1),
            actual = AreaAmount(amount = 1,
                                major = LENGTH1 * LENGTH1) -
                     AreaAmount(amount = 1,
                                major = LENGTH1 * LENGTH1))
    }

    @Test
    fun minusOtherArea()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 4 - (2 * 2),
                                  major = LENGTH1 * LENGTH1),
            actual = AreaAmount(amount = 4,
                                major = LENGTH1 * LENGTH1) -
                     AreaAmount(amount = 1,
                                major = LENGTH2 * LENGTH2))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1) /
                     AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = NaturalAmount(amount = 4 / (2 * 2)),
            actual = AreaAmount(amount = 4,
                                major = LENGTH1 * LENGTH1) /
                     AreaAmount(amount = 1,
                                major = LENGTH2 * LENGTH2))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 / 2,
                                    major = LENGTH1),
            actual = AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1) /
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 / 2,
                                    major = LENGTH1),
            actual = AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1) /
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 2 * 2,
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1) *
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 2 * 2,
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1) *
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }
}