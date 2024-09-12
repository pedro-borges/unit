package org.pcb.unit.amounts

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.units.test.ANGLE1
import org.pcb.unit.units.test.ANGLE2
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.LUMINOUSINTENSITY1
import org.pcb.unit.units.test.LUMINOUSINTENSITY2
import org.pcb.unit.units.test.NUMBER2

class IlluminanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = IlluminanceAmount(amount = 2 * 2,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1),
            actual = IlluminanceAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = IlluminanceAmount(amount = 1,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1),
            actual = IlluminanceAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameIlluminance()
    {
        assertEquals(
            expected = IlluminanceAmount(amount = 1,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1),
            actual = IlluminanceAmount(amount = 0,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1) +
                     IlluminanceAmount(amount = 1,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1))
    }

    @Test
    fun plusOtherIlluminance()
    {
        assertEquals(
            expected = IlluminanceAmount(amount = 1,
                                          unit = U2(LUMINOUSINTENSITY2, ANGLE2) to DISTANCE1),
            actual = IlluminanceAmount(amount = 0,
                                        unit = U2(LUMINOUSINTENSITY2, ANGLE2) to DISTANCE1) +
                     IlluminanceAmount(amount = 100000,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE2))
    }

    @Test
    fun minusSameIlluminance()
    {
        assertEquals(
            expected = IlluminanceAmount(amount = 0,
                                          unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1),
            actual = IlluminanceAmount(amount = 1,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1) -
                     IlluminanceAmount(amount = 1,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1))
    }

    @Test
    fun minusOtherIlluminance()
    {
        assertEquals(
            expected = IlluminanceAmount(amount = 0,
                                          unit = U2(LUMINOUSINTENSITY2, ANGLE2) to DISTANCE1),
            actual = IlluminanceAmount(amount = 1,
                                        unit = U2(LUMINOUSINTENSITY2, ANGLE2) to DISTANCE1) -
                     IlluminanceAmount(amount = 100000,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE2))
    }

    @Test
    fun divSameIlluminance()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = IlluminanceAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1) /
                     IlluminanceAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE1))
    }

    @Test
    fun divOtherIlluminance()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = IlluminanceAmount(amount = 2,
                                        unit = U2(LUMINOUSINTENSITY2, ANGLE2) to DISTANCE1) /
                     IlluminanceAmount(amount = 200000,
                                        unit = U2(LUMINOUSINTENSITY1, ANGLE1) to DISTANCE2))
    }
}