package org.pcb.unit.units

import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2

class AreaAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = AreaAmount(amount = 2 * 2,
                                  unit = LENGTH1),
            actual = AreaAmount(amount = 2,
                                unit = LENGTH1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = LENGTH1),
            actual = AreaAmount(amount = 2,
                                unit = LENGTH1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = LENGTH1),
            actual = AreaAmount(amount = 0,
                                unit = LENGTH1) +
                     AreaAmount(amount = 1,
                                unit = LENGTH1))
    }

    @Test
    fun plusOtherArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 1,
                                  unit = LENGTH2),
            actual = AreaAmount(amount = 0,
                                unit = LENGTH2) +
                     AreaAmount(amount = 100,
                                unit = LENGTH1))
    }

    @Test
    fun minusSameArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 0,
                                  unit = LENGTH1),
            actual = AreaAmount(amount = 1,
                                unit = LENGTH1) -
                     AreaAmount(amount = 1,
                                unit = LENGTH1))
    }

    @Test
    fun minusOtherArea()
    {
        assertEquals(
            expected = AreaAmount(amount = 0,
                                  unit = LENGTH2),
            actual = AreaAmount(amount = 1,
                                unit = LENGTH2) -
                     AreaAmount(amount = 100,
                                unit = LENGTH1))
    }

    @Test
    fun divSameArea()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = AreaAmount(amount = 2,
                                unit = LENGTH1) /
                     AreaAmount(amount = 2,
                                unit = LENGTH1))
    }

    @Test
    fun divOtherArea()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = AreaAmount(amount = 2,
                                unit = LENGTH2) /
                     AreaAmount(amount = 200,
                                unit = LENGTH1))
    }

    @Test
    fun divSameLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH1),
            actual = AreaAmount(amount = 2,
                                unit = LENGTH1) /
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun divOtherLength()
    {
        assertEquals(
            expected = LengthAmount(amount = 1,
                                    unit = LENGTH2),
            actual = AreaAmount(amount = 2,
                                unit = LENGTH2) /
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }

    @Test
    fun timesSameLength()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = LENGTH1),
            actual = AreaAmount(amount = 2,
                                unit = LENGTH1) *
                     LengthAmount(amount = 2,
                                  unit = LENGTH1))
    }

    @Test
    fun timesOtherLength()
    {
        assertEquals(
            expected = VolumeAmount(amount = 2 * 2,
                                    unit = LENGTH2),
            actual = AreaAmount(amount = 2,
                                unit = LENGTH2) *
                     LengthAmount(amount = 20,
                                  unit = LENGTH1))
    }
}