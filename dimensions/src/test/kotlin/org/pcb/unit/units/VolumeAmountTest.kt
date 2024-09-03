package org.pcb.unit.units

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2

class VolumeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = VolumeAmount(2 * 2, LENGTH1, LENGTH1, LENGTH1),
            actual = VolumeAmount(2, LENGTH1, LENGTH1, LENGTH1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = VolumeAmount(2 / 2, LENGTH1, LENGTH1, LENGTH1),
            actual = VolumeAmount(2, LENGTH1, LENGTH1, LENGTH1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameVolume()
    {
        assertEquals(
            expected = VolumeAmount(0 + 1, LENGTH1, LENGTH1, LENGTH1),
            actual = VolumeAmount(0, LENGTH1, LENGTH1, LENGTH1) +
                     VolumeAmount(1, LENGTH1, LENGTH1, LENGTH1))
    }

    @Test
    fun plusOtherVolume()
    {
        assertEquals(
            expected = VolumeAmount(0 + (2 * 2 * 2), LENGTH1, LENGTH1, LENGTH1),
            actual = VolumeAmount(0, LENGTH1, LENGTH1, LENGTH1) +
                     VolumeAmount(1, LENGTH2, LENGTH2, LENGTH2))
    }

    @Test
    fun minusSameVolume()
    {
        assertEquals(
            expected = VolumeAmount(1 - 1, LENGTH1, LENGTH1, LENGTH1),
            actual = VolumeAmount(1, LENGTH1, LENGTH1, LENGTH1) -
                     VolumeAmount(1, LENGTH1, LENGTH1, LENGTH1))
    }

    @Test
    fun minusOtherVolume()
    {
        assertEquals(
            expected = VolumeAmount(8 - (2 * 2 * 2), LENGTH1, LENGTH1, LENGTH1),
            actual = VolumeAmount(8, LENGTH1, LENGTH1, LENGTH1) -
                     VolumeAmount(1, LENGTH2, LENGTH2, LENGTH2))
    }

    @Test
    fun divSameVolume()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = VolumeAmount(2, LENGTH1, LENGTH1, LENGTH1) /
                     VolumeAmount(2, LENGTH1, LENGTH1, LENGTH1))
    }

    @Test
    fun divOtherVolume()
    {
        assertEquals(
            expected = NaturalAmount(8 / (2 * 2 * 2)),
            actual = VolumeAmount(8, LENGTH1, LENGTH1, LENGTH1) /
                     VolumeAmount(1, LENGTH2, LENGTH2, LENGTH2))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = AreaAmount(2 / 2, LENGTH1, LENGTH1),
            actual = VolumeAmount(2, LENGTH1, LENGTH1, LENGTH1) /
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = AreaAmount(2 / 2, LENGTH1, LENGTH1),
            actual = VolumeAmount(2, LENGTH1, LENGTH1, LENGTH1) /
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = LengthAmount(2 / 2, LENGTH1),
            actual = VolumeAmount(2, LENGTH1, LENGTH1, LENGTH1) /
                     AreaAmount(2, LENGTH1, LENGTH1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = LengthAmount(4 / (2 * 2), LENGTH1),
            actual = VolumeAmount(4, LENGTH1, LENGTH1, LENGTH1) /
                     AreaAmount(1, LENGTH2, LENGTH2))
    }
}