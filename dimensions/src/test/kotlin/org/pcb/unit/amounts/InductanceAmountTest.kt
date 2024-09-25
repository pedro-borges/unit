package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.DISTANCE2
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class InductanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = InductanceAmount(amount = 2 * 2,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = InductanceAmount(amount = 1,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameInductance()
    {
        assertEquals(
            expected = InductanceAmount(amount = 1,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = InductanceAmount(amount = 0,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) +
                     InductanceAmount(amount = 1,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun plusOtherInductance()
    {
        assertEquals(
            expected = InductanceAmount(amount = 1,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)),
            actual = InductanceAmount(amount = 0,
                                      unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) +
                     InductanceAmount(amount = 10000000,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun minusSameInductance()
    {
        assertEquals(
            expected = InductanceAmount(amount = 0,
                                        unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = InductanceAmount(amount = 1,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) -
                     InductanceAmount(amount = 1,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun minusOtherInductance()
    {
        assertEquals(
            expected = InductanceAmount(amount = 0,
                                        unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)),
            actual = InductanceAmount(amount = 1,
                                      unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) -
                     InductanceAmount(amount = 10000000,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameInductance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherInductance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     InductanceAmount(amount = 20000000,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME1)),
            actual = InductanceAmount(amount = 2,
                                      unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME1)) *
                     ElectricCurrentAmount(amount = 20,
                                           unit = ELECTRICCURRENT1))
    }
}