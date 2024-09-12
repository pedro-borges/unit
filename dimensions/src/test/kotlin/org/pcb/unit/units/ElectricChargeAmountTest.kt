package org.pcb.unit.units

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

class ElectricChargeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) *
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) /
                     ScalarAmount(amount = 20,
                                  unit = NUMBER2))
    }

    @Test
    fun plusSameElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricChargeAmount(amount = 0,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) +
                     ElectricChargeAmount(amount = 1,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun plusOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = U2(ELECTRICCURRENT2, TIME2)),
            actual = ElectricChargeAmount(amount = 0,
                                          unit = U2(ELECTRICCURRENT2, TIME2)) +
                     ElectricChargeAmount(amount = 100,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun minusSameElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 0,
                                            unit = U2(ELECTRICCURRENT1, TIME1)),
            actual = ElectricChargeAmount(amount = 1,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) -
                     ElectricChargeAmount(amount = 1,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun minusOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 0,
                                            unit = U2(ELECTRICCURRENT2, TIME2)),
            actual = ElectricChargeAmount(amount = 1,
                                          unit = U2(ELECTRICCURRENT2, TIME2)) -
                     ElectricChargeAmount(amount = 100,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = Numeric(amount = 1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT2, TIME2)) /
                     ElectricChargeAmount(amount = 200,
                                          unit = U2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) /
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = CapacitanceAmount(amount = 1,
                                         unit = U2(TIME2, ELECTRICCURRENT2) to U2(MASS2, DISTANCE2)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT2, TIME2)) /
                     ElectricTensionAmount(amount = 20000,
                                           unit = U2(MASS2, DISTANCE2) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divSameCapacitance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) /
                     CapacitanceAmount(amount = 2,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS1, DISTANCE1)))
    }

    @Test
    fun divOtherCapacitance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT2, TIME2)) /
                     CapacitanceAmount(amount = 2000000,
                                       unit = U2(TIME1, ELECTRICCURRENT1) to U2(MASS2, DISTANCE2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS1, DISTANCE1) to TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = U2(MASS2, DISTANCE2) to TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricTensionAmount(amount = 20000,
                                           unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = U2(MASS1, DISTANCE1) to U2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = U2(MASS2, DISTANCE2) to U2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = U2(ELECTRICCURRENT1, TIME1)) *
                     ElectricResistanceAmount(amount = 200000,
                                              unit = U2(MASS2, DISTANCE2) to U2(TIME2, ELECTRICCURRENT2)))
    }
}