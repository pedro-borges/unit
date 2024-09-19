package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.units.test.ANGLE1
import org.pcb.unit.units.test.ANGLE2
import org.pcb.unit.units.test.NUMBER2

class AngleAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = AngleAmount(amount = 2 * 2,
                                   unit = ANGLE1),
            actual = AngleAmount(amount = 2,
                                 unit = ANGLE1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = AngleAmount(amount = 1,
                                   unit = ANGLE1),
            actual = AngleAmount(amount = 2,
                                 unit = ANGLE1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameAngle()
    {
        assertEquals(
            expected = AngleAmount(amount = 1,
                                   unit = ANGLE1),
            actual = AngleAmount(amount = 0,
                                 unit = ANGLE1) +
                     AngleAmount(amount = 1,
                                 unit = ANGLE1))
    }

    @Test
    fun plusOtherAngle()
    {
        assertEquals(
            expected = AngleAmount(amount = 1,
                                   unit = ANGLE2),
            actual = AngleAmount(amount = 0,
                                 unit = ANGLE2) +
                     AngleAmount(amount = 10,
                                 unit = ANGLE1))
    }

    @Test
    fun minusSameAngle()
    {
        assertEquals(
            expected = AngleAmount(amount = 0,
                                   unit = ANGLE1),
            actual = AngleAmount(amount = 1,
                                 unit = ANGLE1) -
                     AngleAmount(amount = 1,
                                 unit = ANGLE1))
    }

    @Test
    fun minusOtherAngle()
    {
        assertEquals(
            expected = AngleAmount(amount = 0,
                                   unit = ANGLE2),
            actual = AngleAmount(amount = 1,
                                 unit = ANGLE2) -
                     AngleAmount(amount = 10,
                                 unit = ANGLE1))
    }

    @Test
    fun divSameAngle()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = AngleAmount(amount = 2,
                                 unit = ANGLE1) /
                     AngleAmount(amount = 2,
                                 unit = ANGLE1))
    }

    @Test
    fun divOtherAngle()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = AngleAmount(amount = 2,
                                 unit = ANGLE2) /
                     AngleAmount(amount = 20,
                                 unit = ANGLE1))
    }

    @Test
    fun timesSameAngle()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 2 * 2,
                                        unit = ANGLE1),
            actual = AngleAmount(amount = 2,
                                 unit = ANGLE1) *
                     AngleAmount(amount = 2,
                                 unit = ANGLE1))
    }

    @Test
    fun timesOtherAngle()
    {
        assertEquals(
            expected = SolidAngleAmount(amount = 2 * 2,
                                        unit = ANGLE2),
            actual = AngleAmount(amount = 2,
                                 unit = ANGLE2) *
                     AngleAmount(amount = 20,
                                 unit = ANGLE1))
    }
}