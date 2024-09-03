package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2

class VolumeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 2 * 2,
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 2 / 2,
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameVolume()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 0 + 1,
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = VolumeAmount(amount = 0,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) +
                     VolumeAmount(amount = 1,
                                  major = LENGTH1 * LENGTH1 * LENGTH1))
    }

    @Test
    fun plusOtherVolume()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 0 + (2 * 2 * 2),
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = VolumeAmount(amount = 0,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) +
                     VolumeAmount(amount = 1,
                                  major = LENGTH2 * LENGTH2 * LENGTH2))
    }

    @Test
    fun minusSameVolume()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 1 - 1,
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = VolumeAmount(amount = 1,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) -
                     VolumeAmount(amount = 1,
                                  major = LENGTH1 * LENGTH1 * LENGTH1))
    }

    @Test
    fun minusOtherVolume()
    {
        assertEquals<VolumeAmount<out Unit<Length>>>(
            expected = VolumeAmount(amount = 8 - (2 * 2 * 2),
                                    major = LENGTH1 * LENGTH1 * LENGTH1),
            actual = VolumeAmount(amount = 8,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) -
                     VolumeAmount(amount = 1,
                                  major = LENGTH2 * LENGTH2 * LENGTH2))
    }

    @Test
    fun divSameVolume()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = VolumeAmount(amount = 2,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) /
                     VolumeAmount(amount = 2,
                                  major = LENGTH1 * LENGTH1 * LENGTH1))
    }

    @Test
    fun divOtherVolume()
    {
        assertEquals(
            expected = NaturalAmount(amount = 8 / (2 * 2 * 2)),
            actual = VolumeAmount(amount = 8,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) /
                     VolumeAmount(amount = 1,
                                  major = LENGTH2 * LENGTH2 * LENGTH2))
    }

    @Test
    fun divSameLength()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 2 / 2,
                                  major = LENGTH1 * LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) /
                     LengthAmount(amount = 2,
                                  major = LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals<AreaAmount<out Unit<Length>>>(
            expected = AreaAmount(amount = 2 / 2,
                                  major = LENGTH1 * LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) /
                     LengthAmount(amount = 1,
                                  major = LENGTH2))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = LengthAmount(amount = 2 / 2,
                                    major = LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) /
                     AreaAmount(amount = 2,
                                major = LENGTH1 * LENGTH1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = LengthAmount(amount = 4 / (2 * 2),
                                    major = LENGTH1),
            actual = VolumeAmount(amount = 4,
                                  major = LENGTH1 * LENGTH1 * LENGTH1) /
                     AreaAmount(amount = 1,
                                major = LENGTH2 * LENGTH2))
    }
}