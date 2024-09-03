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
            expected = AreaAmount(2 * 2, LENGTH1 * LENGTH1),
            actual = AreaAmount(2, LENGTH1 * LENGTH1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(2 / 2, LENGTH1 * LENGTH1),
            actual = AreaAmount(2, LENGTH1 * LENGTH1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameArea()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(0 + 1, LENGTH1 * LENGTH1),
            actual = AreaAmount(0, LENGTH1 * LENGTH1) +
                     AreaAmount(1, LENGTH1 * LENGTH1))
    }

    @Test
    fun plusOtherArea()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(0 + (2 * 2), LENGTH1 * LENGTH1),
            actual = AreaAmount(0, LENGTH1 * LENGTH1) +
                     AreaAmount(1, LENGTH2 * LENGTH2))
    }

    @Test
    fun minusSameArea()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(1 - 1, LENGTH1 * LENGTH1),
            actual = AreaAmount(1, LENGTH1 * LENGTH1) -
                     AreaAmount(1, LENGTH1 * LENGTH1))
    }

    @Test
    fun minusOtherArea()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(4 - (2 * 2), LENGTH1 * LENGTH1),
            actual = AreaAmount(4, LENGTH1 * LENGTH1) -
                     AreaAmount(1, LENGTH2 * LENGTH2))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = AreaAmount(2, LENGTH1 * LENGTH1) /
                     AreaAmount(2, LENGTH1 * LENGTH1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = NaturalAmount(4 / (2 * 2)),
            actual = AreaAmount(4, LENGTH1 * LENGTH1) /
                     AreaAmount(1, LENGTH2 * LENGTH2))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = LengthAmount(2 / 2, LENGTH1),
            actual = AreaAmount(2, LENGTH1 * LENGTH1) /
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = LengthAmount(2 / 2, LENGTH1),
            actual = AreaAmount(2, LENGTH1 * LENGTH1) /
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals(
            expected = VolumeAmount(2 * 2, (LENGTH1 * LENGTH1 * LENGTH1)),
            actual = AreaAmount(2, LENGTH1 * LENGTH1) *
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals(
            expected = VolumeAmount(2 * 2, (LENGTH1 * LENGTH1 * LENGTH1)),
            actual = AreaAmount(2, LENGTH1 * LENGTH1) *
                     LengthAmount(1, LENGTH2))
    }
}