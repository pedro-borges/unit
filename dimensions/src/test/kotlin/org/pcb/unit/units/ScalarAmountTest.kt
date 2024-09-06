package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.T2
import org.pcb.unit.units.scalar.Numeric.ONE
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.scalar.Percent.PERCENT
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.TIME1

class ScalarAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = ONE),
            actual = ScalarAmount(amount = 2,
                                  unit = ONE) *
                     ScalarAmount(amount = 2 * 100,
                                  unit = PERCENT))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = ONE),
            actual = ScalarAmount(amount = 2,
                                  unit = ONE) /
                     ScalarAmount(amount = 2 * 100,
                                  unit = PERCENT))
    }

    @Test
    fun plusSameScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = ONE),
            actual = ScalarAmount(amount = 0,
                                  unit = ONE) +
                     ScalarAmount(amount = 1,
                                  unit = ONE))
    }

    @Test
    fun plusOtherScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = ONE),
            actual = ScalarAmount(amount = 0,
                                  unit = ONE) +
                     ScalarAmount(amount = 100,
                                  unit = PERCENT))
    }

    @Test
    fun minusSameScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 0,
                                    unit = ONE),
            actual = ScalarAmount(amount = 1,
                                  unit = ONE) -
                     ScalarAmount(amount = 1,
                                  unit = ONE))
    }

    @Test
    fun minusOtherScalar()
    {
        assertEquals(
            expected = ScalarAmount(amount = 0,
                                    unit = ONE),
            actual = ScalarAmount(amount = 1,
                                  unit = ONE) -
                     ScalarAmount(amount = 100,
                                  unit = PERCENT))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = ONE to TIME1),
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
                                     unit = ONE to TIME1))
    }

    @Test
    fun divElectricConductivity()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 2 * 2,
                                                unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = Percent(amount = 2 * 100) /
                     ElectricConductivityAmount(amount = 2,
                                                unit = T2(TIME1, ELECTRICCURRENT1) to T2(MASS1, LENGTH1)))
    }

    @Test
    fun divElectricResistance()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = T2(TIME1, ELECTRICCURRENT1) to T2(MASS1, LENGTH1)),
            actual = Percent(amount = 2 * 100) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)))
    }
}