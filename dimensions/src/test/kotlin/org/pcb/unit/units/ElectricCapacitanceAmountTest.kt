package org.pcb.unit

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricCapacitanceAmount
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.ElectricTensionAmount
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

class ElectricCapacitanceAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 2 * 2,
                                                 unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)) *
                     Percent(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 1,
                                                 unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)) /
                     Percent(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricCapacitance()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 1,
                                                 unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 0,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)) +
                     ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)))
    }

    @Test
    fun plusOtherElectricCapacitance()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 1,
                                                 unit = (TIME2 * ELECTRICCURRENT2) / (MASS1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 0,
                                               unit = (TIME2 * ELECTRICCURRENT2) / (MASS1 * LENGTH1)) +
                     ElectricCapacitanceAmount(amount = 1000000000,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS2 * LENGTH2)))
    }

    @Test
    fun minusSameElectricCapacitance()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 0,
                                                 unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)) -
                     ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)))
    }

    @Test
    fun minusOtherElectricCapacitance()
    {
        assertEquals(
            expected = ElectricCapacitanceAmount(amount = 0,
                                                 unit = (TIME2 * ELECTRICCURRENT2) / (MASS1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME2 * ELECTRICCURRENT2) / (MASS1 * LENGTH1)) -
                     ElectricCapacitanceAmount(amount = 1000000000,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS2 * LENGTH2)))
    }

    @Test
    fun divSameElectricCapacitance()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS1 * LENGTH1)))
    }

    @Test
    fun divOtherElectricCapacitance()
    {
        assertEquals(
            expected = BigDecimal(1),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME2 * ELECTRICCURRENT2) / (MASS1 * LENGTH1)) /
                     ElectricCapacitanceAmount(amount = 2000000000,
                                               unit = (TIME1 * ELECTRICCURRENT1) / (MASS2 * LENGTH2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = Tuple(ELECTRICCURRENT1, TIME1)),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = Tuple(TIME1, ELECTRICCURRENT1) / Tuple(MASS1, LENGTH1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = Tuple(ELECTRICCURRENT1, TIME1)),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = Tuple(TIME1, ELECTRICCURRENT1) / Tuple(MASS1, LENGTH1)) *
                     ElectricTensionAmount(amount = 20,
                                           unit = Tuple(MASS2, LENGTH2) / Tuple(TIME2, ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = Tuple(TIME1, ELECTRICCURRENT1) / Tuple(MASS1, LENGTH1)) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = Tuple(MASS1, LENGTH1) / Tuple(TIME1, ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = Tuple(TIME1, ELECTRICCURRENT1) / Tuple(MASS1, LENGTH1)) *
                     ElectricResistanceAmount(amount = 200,
                                              unit = Tuple(MASS2, LENGTH2) / Tuple(TIME2, ELECTRICCURRENT2)))
    }
}