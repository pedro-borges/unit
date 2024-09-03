package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricCapacitanceAmount
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
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
    fun construct()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, out Unit<ElectricCurrent>, MASS1, out Unit<Length>>>(
            expected = ElectricCapacitanceAmount(amount = 2 * 2 * 2,
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME1 * TIME2 * TIME2 * TIME2 * ELECTRICCURRENT1 * ELECTRICCURRENT2) /
                                                      (MASS1 * LENGTH1 * LENGTH2)))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricCapacitanceAmount(amount = 2 * 2,
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricCapacitanceAmount(amount = 2 / 2,
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricCapacitance()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricCapacitanceAmount(amount = 0 + 1,
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 0,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) +
                     ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun plusOtherElectricCapacitance()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricCapacitanceAmount(amount = 0 + (2 * 2 * 2),
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 0,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) +
                     ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME2 * TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                      (MASS2 * LENGTH2 * LENGTH2)))
    }

    @Test
    fun minusSameElectricCapacitance()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricCapacitanceAmount(amount = 1 - 1,
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) -
                     ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun minusOtherElectricCapacitance()
    {
        assertEquals<ElectricCapacitanceAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricCapacitanceAmount(amount = 8 - (2 * 2 * 2),
                                                 unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                        (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricCapacitanceAmount(amount = 8,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) -
                     ElectricCapacitanceAmount(amount = 1,
                                               unit = (TIME2 * TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                      (MASS2 * LENGTH2 * LENGTH2)))
    }

    @Test
    fun divSameElectricCapacitance()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun divOtherElectricCapacitance()
    {
        assertEquals(
            expected = NaturalAmount(amount = 16 / (2 * 2 * 2 * 2)),
            actual = ElectricCapacitanceAmount(amount = 16,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) /
                     ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME2 * TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                      (MASS2 * LENGTH2 * LENGTH2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                  (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) *
                     ElectricTensionAmount(amount = 2 * 2,
                                           unit = (MASS2 * LENGTH2 * LENGTH2) /
                                                  (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = TimeAmount(amount = 2 * 2,
                                  unit = TIME1),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME1 * TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                      (MASS1 * LENGTH1 * LENGTH1)) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                     (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals<TimeAmount<out Unit<Time>>>(
            expected = TimeAmount(amount = 16 * (2 * 2 * 2 * 2),
                                  unit = TIME1),
            actual = ElectricCapacitanceAmount(amount = 2,
                                               unit = (TIME2 * TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                      (MASS2 * LENGTH2 * LENGTH2)) *
                     ElectricResistanceAmount(amount = 2 * (2 * 2 * 2),
                                              unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                     (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)))
    }
}