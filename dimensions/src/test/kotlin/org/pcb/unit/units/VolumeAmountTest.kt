package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2

class VolumeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  unit = LENGTH1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = VolumeAmount(amount = 1,
                                    unit = LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  unit = LENGTH1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameVolume()
    {
        assertEquals(
            expected = VolumeAmount(amount = 1,
                                    unit = LENGTH1),
            actual = VolumeAmount(amount = 0,
                                  unit = LENGTH1) +
                     VolumeAmount(amount = 1,
                                  unit = LENGTH1))
    }

    @Test
    fun plusOtherVolume()
    {
        assertEquals(
            expected = VolumeAmount(amount = 1,
                                    unit = LENGTH2),
            actual = VolumeAmount(amount = 0,
                                  unit = LENGTH2) +
                     VolumeAmount(amount = 1000,
                                  unit = LENGTH1))
    }

    @Test
    fun minusSameVolume()
    {
        assertEquals(
            expected = VolumeAmount(amount = 0,
                                    unit = LENGTH1),
            actual = VolumeAmount(amount = 1,
                                  unit = LENGTH1) -
                     VolumeAmount(amount = 1,
                                  unit = LENGTH1))
    }

    @Test
    fun minusOtherVolume()
    {
        assertEquals(
            expected = VolumeAmount(amount = 0,
                                    unit = LENGTH2),
            actual = VolumeAmount(amount = 1,
                                  unit = LENGTH2) -
                     VolumeAmount(amount = 1000,
                                  unit = LENGTH1))
    }

    @Test
    fun divSameVolume()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = VolumeAmount(amount = 2,
                                  unit = LENGTH1) /
                     VolumeAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun divOtherVolume()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = VolumeAmount(amount = 2,
                                  unit = LENGTH2) /
                     VolumeAmount(amount = 2000,
                                  unit = LENGTH1))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  unit = LENGTH1) /
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = LENGTH2),
            actual = VolumeAmount(amount = 2,
                                  unit = LENGTH2) /
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH1),
            actual = VolumeAmount(amount = 2,
                                  unit = LENGTH1) /
                     AreaAmount(amount = 2,
                                unit = LENGTH1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH2),
            actual = VolumeAmount(amount = 2,
                                  unit = LENGTH2) /
                     AreaAmount(amount = 200,
                                unit = LENGTH1))
    }
}