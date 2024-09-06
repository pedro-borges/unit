package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricCapacitanceAmount
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
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

class ElectricChargeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 0,
                                          unit = ELECTRICCURRENT1 * TIME1) +
                     ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun plusOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 1,
                                            unit = ELECTRICCURRENT2 * TIME2),
            actual = ElectricChargeAmount(amount = 0,
                                          unit = ELECTRICCURRENT2 * TIME2) +
                     ElectricChargeAmount(amount = 100,
                                          unit = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun minusSameElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 0,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT1 * TIME1) -
                     ElectricChargeAmount(amount = 1,
                                          unit = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun minusOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 0,
                                            unit = Tuple(ELECTRICCURRENT2, TIME2)),
            actual = ElectricChargeAmount(amount = 1,
                                          unit = Tuple(ELECTRICCURRENT2, TIME2)) -
                     ElectricChargeAmount(amount = 100,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) /
                     ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = Tuple(ELECTRICCURRENT2, TIME2)) /
                     ElectricChargeAmount(amount = 200,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)) /
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 1,
                                                 unit = Tuple(TIME1, ELECTRICCURRENT1) / Tuple(MASS1, LENGTH1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)) /
                     ElectricTensionAmount(amount = 2,
                                           unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 1,
                                                 unit = Tuple(TIME1, ELECTRICCURRENT1) / Tuple(MASS2, LENGTH2)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)) /
                     ElectricTensionAmount(amount = 20000,
                                           unit = Tuple(MASS2, LENGTH2) / Tuple(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun divSameElectricCapacitance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 1,
                                             unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = Tuple(TIME1, ELECTRICCURRENT1) / Tuple(MASS1, LENGTH1)))
    }

    @Test
    fun divOtherElectricCapacitance()
    {
        assertEquals(
            expected = ElectricTensionAmount(amount = 128 / 128,
                                             unit = Tuple(MASS2, LENGTH2) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2000000,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = Tuple(TIME2, ELECTRICCURRENT2) / Tuple(MASS2, LENGTH2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = (MASS1 * LENGTH1) / TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = ELECTRICCURRENT1 * TIME1) *
                     ElectricTensionAmount(amount = 2,
                                           unit = (MASS1 * LENGTH1) / (TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = EnergyAmount(amount = 2 * 2,
                                    unit = Tuple(MASS2, LENGTH2) / TIME1),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)) *
                     ElectricTensionAmount(amount = 20000,
                                           unit = Tuple(MASS2, LENGTH2) / Tuple(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = MagneticFluxAmount(amount = 2 * 2,
                                          unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)),
            actual = ElectricChargeAmount(amount = 2,
                                          unit = Tuple(ELECTRICCURRENT1, TIME1)) *
                     ElectricResistanceAmount(amount = 200000,
                                              unit = Tuple(MASS1, LENGTH1) / Tuple(TIME2, ELECTRICCURRENT2)))
    }
}