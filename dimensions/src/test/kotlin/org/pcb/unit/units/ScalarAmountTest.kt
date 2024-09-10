package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.scalar.Percent.PERCENT
import org.pcb.unit.units.test.DISTANCE1
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.TIME1

class ScalarAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = NUMERIC),
            actual = ScalarAmount(amount = 2,
                                  unit = NUMERIC) *
                     ScalarAmount(amount = 2 * 100,
                                  unit = PERCENT))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ScalarAmount(amount = 2,
                                  unit = NUMERIC) /
                     ScalarAmount(amount = 2 * 100,
                                  unit = PERCENT))
    }

    @Test
    fun plusSameScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ScalarAmount(amount = 0,
                                  unit = NUMERIC) +
                     ScalarAmount(amount = 1,
                                  unit = NUMERIC))
    }

    @Test
    fun plusOtherScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ScalarAmount(amount = 0,
                                  unit = NUMERIC) +
                     ScalarAmount(amount = 100,
                                  unit = PERCENT))
    }

    @Test
    fun minusSameScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 0,
                                    unit = NUMERIC),
            actual = ScalarAmount(amount = 1,
                                  unit = NUMERIC) -
                     ScalarAmount(amount = 1,
                                  unit = NUMERIC))
    }

    @Test
    fun minusOtherScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 0,
                                    unit = NUMERIC),
            actual = ScalarAmount(amount = 1,
                                  unit = NUMERIC) -
                     ScalarAmount(amount = 100,
                                  unit = PERCENT))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMERIC to TIME1),
            actual = Percent(amount = 2 * 100) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divFrequency()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = Percent(amount = 2 * 100) /
                     FrequencyAmount(amount = 2,
                                     unit = NUMERIC to TIME1))
    }

    @Test
    fun divElectricConductance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 2 * 2,
                                                unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = Percent(amount = 2 * 100) /
                     ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divElectricResistance()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = Percent(amount = 2 * 100) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }
}