package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricConductivityAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricTensionAmount
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

class ElectricConductivityAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ElectricConductivityAmount<out Unit<Time>, out Unit<ElectricCurrent>, MASS1, out Unit<Length>>>(
            expected = ElectricConductivityAmount(amount = 2 * 2 * 2 / 2,
                                                  unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                         (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricConductivityAmount(amount = 1,
                                                unit = (TIME1 * TIME2 * TIME2 * ELECTRICCURRENT1 * ELECTRICCURRENT2) /
                                                       (MASS1 * LENGTH1 * LENGTH2)))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<ElectricConductivityAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricConductivityAmount(amount = 2 * 2,
                                                  unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                         (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<ElectricConductivityAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricConductivityAmount(amount = 2 / 2,
                                                  unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                         (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricConductivity()
    {
        assertEquals<ElectricConductivityAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricConductivityAmount(amount = 0 + 1,
                                                  unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                         (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricConductivityAmount(amount = 0,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) +
                     ElectricConductivityAmount(amount = 1,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun plusOtherElectricConductivity()
    {
        assertEquals<ElectricConductivityAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricConductivityAmount(amount = 0 + (2 * 2),
                                                  unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                         (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricConductivityAmount(amount = 0,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) +
                     ElectricConductivityAmount(amount = 1,
                                                unit = (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                       (MASS2 * LENGTH2 * LENGTH2)))
    }

    @Test
    fun minusSameElectricConductivity()
    {
        assertEquals<ElectricConductivityAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricConductivityAmount(amount = 1 - 1,
                                                  unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                         (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricConductivityAmount(amount = 1,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) -
                     ElectricConductivityAmount(amount = 1,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun minusOtherElectricConductivity()
    {
        assertEquals<ElectricConductivityAmount<out Unit<Time>, ELECTRICCURRENT1, out Unit<Mass>, LENGTH1>>(
            expected = ElectricConductivityAmount(amount = 4 - (2 * 2),
                                                  unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                         (MASS1 * LENGTH1 * LENGTH1)),
            actual = ElectricConductivityAmount(amount = 4,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) -
                     ElectricConductivityAmount(amount = 1,
                                                unit = (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                       (MASS2 * LENGTH2 * LENGTH2)))
    }

    @Test
    fun divSameElectricConductivity()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) /
                     ElectricConductivityAmount(amount = 2,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)))
    }

    @Test
    fun divOtherElectricConductivity()
    {
        assertEquals(
            expected = NaturalAmount(amount = 8 / (2 * 2 * 2)),
            actual = ElectricConductivityAmount(amount = 2 * (2 * 2),
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) /
                     ElectricConductivityAmount(amount = 2,
                                                unit = (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2) /
                                                       (MASS2 * LENGTH2 * LENGTH2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricConductivityAmount(amount = 2,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                  (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = (2 * 2) * 4,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricConductivityAmount(amount = 2 * 2,
                                                unit = (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                                                       (MASS1 * LENGTH1 * LENGTH1)) *
                     ElectricTensionAmount(amount = 2,
                                           unit = (MASS2 * LENGTH2 * LENGTH2) /
                                                  (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2)))
    }
}