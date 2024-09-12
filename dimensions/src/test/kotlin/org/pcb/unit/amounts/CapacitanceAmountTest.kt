package org.pcb.unit.amounts

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.base.Numeric
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

class CapacitanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 2 * 2,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameCapacitance()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 0,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) +
                     CapacitanceAmount(amount = 1,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun plusOtherCapacitance()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 0,
                                       unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) +
                     CapacitanceAmount(amount = 1000000000,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun minusSameCapacitance()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 0,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 1,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) -
                     CapacitanceAmount(amount = 1,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun minusOtherCapacitance()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 0,
                                         unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 1,
                                       unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) -
                     CapacitanceAmount(amount = 1000000000,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun divSameCapacitance()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherCapacitance()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) /
                     CapacitanceAmount(amount = 2000000000,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)) *
                     ElectricTensionAmount(amount = 20000000,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)) *
                     ElectricResistanceAmount(amount = 200000000,
                                              unit = U2(MASS1, DISTANCE1) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameFrequency()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 2 * 2,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     FrequencyAmount(amount = 2,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun timesOtherFrequency()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 2 * 2,
                                                 unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) *
                     FrequencyAmount(amount = 20,
                                     unit = NUMBER1 to TIME1))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ElectricConductanceAmount(amount = 1,
                                                 unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME2, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }

    @Test
    fun divSameElectricConductance()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME1),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)) /
                     ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherConductivity()
    {
        assertEquals(
            expected = TimeAmount(amount = 1,
                                  unit = TIME2),
            actual = CapacitanceAmount(amount = 2,
                                       unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS1, DISTANCE1)) /
                     ElectricConductanceAmount(amount = 200000000,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }
}