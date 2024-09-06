package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricCapacitanceAmount
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricConductivityAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.MagneticFluxAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.Percent
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ElectricResistanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 2 * 2,
                                                unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricResistanceAmount(amount = 0,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) +
                     ElectricResistanceAmount(amount = 1,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun plusOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 1,
                                                unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricResistanceAmount(amount = 0,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) +
                     ElectricResistanceAmount(amount = 100,
                                              unit = T2(MASS2, LENGTH2) to T2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun minusSameElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 0,
                                                unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricResistanceAmount(amount = 1,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) -
                     ElectricResistanceAmount(amount = 1,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun minusOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(amount = 0,
                                                unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricResistanceAmount(amount = 1,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) -
                     ElectricResistanceAmount(amount = 100,
                                              unit = T2(MASS2, LENGTH2) to T2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) /
                     ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) /
                     ElectricResistanceAmount(amount = 200,
                                              unit = T2(MASS2, LENGTH2) to T2(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricConductivity()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) *
                     ElectricConductivityAmount(amount = 2,
                                                unit = T2(TIME1, ELECTRICCURRENT1) to T2(MASS1, LENGTH1)))
    }

    @Test
    fun timesOtherElectricConductivity()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS2, LENGTH2) to T2(TIME1, ELECTRICCURRENT1)) *
                     ElectricConductivityAmount(amount = 200000000,
                                                unit = T2(TIME2, ELECTRICCURRENT2) to T2(MASS1, LENGTH1)))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) *
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT2)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT2)) *
                     ElectricCurrentAmount(amount = 20,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun timesSameElectricCapacitance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) *
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = T2(TIME1, ELECTRICCURRENT1) to T2(MASS1, LENGTH1)))
    }

    @Test
    fun timesOtherElectricCapacitance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT2)) *
                     ElectricCapacitanceAmount(amount = 20,
                                               unit = T2(TIME2, ELECTRICCURRENT1) to T2(MASS2, LENGTH2)))
    }

    @Test
    fun timesSameElectricCharge()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME1, ELECTRICCURRENT1)) *
                     ElectricChargeAmount(amount = 2,
                                          unit = T2(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun timesOtherElectricCharge()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = T2(MASS1, LENGTH1) to T2(TIME2, ELECTRICCURRENT2)),
            actual = ElectricResistanceAmount(amount = 2,
                                              unit = T2(MASS1, LENGTH1) to T2(TIME2, ELECTRICCURRENT2)) *
                     ElectricChargeAmount(amount = 200,
                                          unit = T2(ELECTRICCURRENT1, TIME1)))
    }
}