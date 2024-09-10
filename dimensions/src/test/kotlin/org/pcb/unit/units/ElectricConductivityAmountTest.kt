package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.units.scalar.Numeric.NUMERIC
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ElectricConductivityAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 2 * 2,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricConductivity()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)),
            actual = ElectricConductivityAmount(amount = 0,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)) +
                     ElectricConductivityAmount(amount = 1,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)))
    }

    @Test
    fun plusOtherElectricConductivity()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 1,
                                                  unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS2, LENGTH2)),
            actual = ElectricConductivityAmount(amount = 0,
                                                unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS2, LENGTH2)) +
                     ElectricConductivityAmount(amount = 100,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)))
    }

    @Test
    fun minusSameElectricConductivity()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 0,
                                                  unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)),
            actual = ElectricConductivityAmount(amount = 1,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)) -
                     ElectricConductivityAmount(amount = 1,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)))
    }

    @Test
    fun minusOtherElectricConductivity()
    {
        assertEquals(
            expected = ElectricConductivityAmount(amount = 0,
                                                  unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS2, LENGTH2)),
            actual = ElectricConductivityAmount(amount = 1,
                                                unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS2, LENGTH2)) -
                     ElectricConductivityAmount(amount = 100,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)))
    }

    @Test
    fun divSameElectricConductivity()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)) /
                     ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)))
    }

    @Test
    fun divOtherElectricConductivity()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, LENGTH1)) /
                     ElectricConductivityAmount(amount = 200000000,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, LENGTH2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)) *
                     ElectricResistanceAmount(amount = 200,
                                              unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, LENGTH1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, LENGTH1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT2),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = U2(TIME1, ELECTRICCURRENT2) to U2(MASS1, LENGTH1)) *
                     ElectricTensionAmount(amount = 20,
                                           unit = U2(MASS2, LENGTH2) to U2(TIME2, ELECTRICCURRENT1)))
    }
}