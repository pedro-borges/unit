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
import org.pcb.unit.units.test.NUMBER1
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ElectricConductanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 2 * 2,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameElectricConductance()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 0,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) +
                     ElectricConductanceAmount(amount = 1,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun plusOtherElectricConductance()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 0,
                                               unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) +
                     ElectricConductanceAmount(amount = 100000000,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun minusSameElectricConductance()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 0,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 1,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) -
                     ElectricConductanceAmount(amount = 1,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun minusOtherElectricConductance()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 0,
                                                 unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 1,
                                               unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) -
                     ElectricConductanceAmount(amount = 100000000,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun divSameElectricConductance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherElectricConductance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) /
                     ElectricConductanceAmount(amount = 200000000,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = NUMERIC),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = NUMERIC),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)) *
                     ElectricResistanceAmount(amount = 200000000,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)) *
                     ElectricTensionAmount(amount = 20000000,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameFrequency()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun divOtherFrequency()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     FrequencyAmount(amount = 20,
                                     unit = NUMBER1 to TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 2 * 2,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 2 * 2,
                                         unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameCapacitance()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMBER1 to TIME1),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherCapacitance()
    {
        assertEquals(
            expected = FrequencyAmount(amount = 1,
                                       unit = NUMBER1 to TIME2),
            actual = ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) /
                     CapacitanceAmount(amount = 2000000000,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }
}