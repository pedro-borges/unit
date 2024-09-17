package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.NUMBER1
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1

class ScalarAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = NUMBER1),
            actual = ScalarAmount(amount = 2,
                                  unit = NUMBER1) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMBER1),
            actual = ScalarAmount(amount = 2,
                                  unit = NUMBER1) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMBER1),
            actual = ScalarAmount(amount = 0,
                                  unit = NUMBER1) +
                     ScalarAmount(amount = 1,
                                  unit = NUMBER1))
    }

    @Test
    fun plusOtherScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMBER1),
            actual = ScalarAmount(amount = 0,
                                  unit = NUMBER1) +
                     ScalarAmount(amount = 10,
                                  unit = NUMBER2))
    }

    @Test
    fun minusSameScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 0,
                                    unit = NUMBER1),
            actual = ScalarAmount(amount = 1,
                                  unit = NUMBER1) -
                     ScalarAmount(amount = 1,
                                  unit = NUMBER1))
    }

    @Test
    fun minusOtherScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 0,
                                    unit = NUMBER1),
            actual = ScalarAmount(amount = 1,
                                  unit = NUMBER1) -
                     ScalarAmount(amount = 10,
                                  unit = NUMBER2))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMBER1 to TIME1),
            actual = ScalarAmount(amount = 2,
                                  unit = NUMBER1) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divFrequency()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = ScalarAmount(amount = 2,
                                  unit = NUMBER1) /
                     FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun divElectricConductance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ScalarAmount(amount = 2,
                                  unit = NUMBER1) /
                     ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divElectricResistance()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ScalarAmount(amount = 2,
                                  unit = NUMBER1) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }
}