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
import org.pcb.unit.units.test.NUMBER2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ElectricTensionAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 0,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) +
                     ElectricTensionAmount(amount = 1,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun plusOtherElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 0,
                                           unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) +
                     ElectricTensionAmount(amount = 10000000,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun minusSameElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 0,
                                             unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 1,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) -
                     ElectricTensionAmount(amount = 1,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun minusOtherElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 0,
                                             unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 1,
                                           unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) -
                     ElectricTensionAmount(amount = 10000000,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricTensionAmount(amount = 20000000,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesSameElectricCharge()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(DISTANCE1, MASS1) to TIME1),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun timesOtherElectricCharge()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(DISTANCE1, MASS1) to TIME2),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)) *
                     ElectricChargeAmount(amount = 200,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME1)),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME1))
                     / ElectricCurrentAmount(amount = 20,
                                             unit = ELECTRICCURRENT1))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE2, MASS2) to U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricResistanceAmount(amount = 200000000,
                                              unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)))
    }

    @Test
    fun timesSameElectricConductance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricConductanceAmount(amount = 2,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun timesOtherElectricConductance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT2),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)) *
                     ElectricConductanceAmount(amount = 200000000,
                                               unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(DISTANCE1, MASS1) to TIME1),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = U2(DISTANCE1, MASS1) to TIME1),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME1)) *
                     ElectricCurrentAmount(amount = 20,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesSameCapacitance()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun timesOtherCapacitance()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = U2(ELECTRICCURRENT2, TIME2)),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT2, TIME2)) *
                     CapacitanceAmount(amount = 2000000000,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME1)) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME2)),
            actual = ElectricTensionAmount(amount = 2,
                                           unit = U2(DISTANCE1, MASS1) to U2(ELECTRICCURRENT1, TIME2)) *
                     TimeAmount(amount = 20,
                                unit = TIME1))
    }
}