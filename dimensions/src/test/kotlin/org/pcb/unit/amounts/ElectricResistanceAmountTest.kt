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

class ElectricResistanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 2 * 2,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricResistanceAmount(amount = 0,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) +
                     ElectricResistanceAmount(amount = 1,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun plusOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricResistanceAmount(amount = 0,
                                              unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) +
                     ElectricResistanceAmount(amount = 100000000,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun minusSameElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 0,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricResistanceAmount(amount = 1,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) -
                     ElectricResistanceAmount(amount = 1,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun minusOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 0,
                                                unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricResistanceAmount(amount = 1,
                                              unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) -
                     ElectricResistanceAmount(amount = 100000000,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 1,
                                    unit = NUMERIC),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricResistanceAmount(amount = 200000000,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesSameElectricConductance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = NUMERIC),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun timesOtherElectricConductance()
    {
        assertEquals(
            expected = ScalarAmount(amount = 2 * 2,
                                    unit = NUMERIC),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)) *
                     ElectricConductanceAmount(amount = 200000000,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME1)) *
                     ElectricCurrentAmount(amount = 20,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesSameCapacitance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun timesOtherCapacitance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME2),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)) *
                     CapacitanceAmount(amount = 2000000000,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricCharge()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun timesOtherElectricCharge()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)) *
                     ElectricChargeAmount(amount = 200,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }
}