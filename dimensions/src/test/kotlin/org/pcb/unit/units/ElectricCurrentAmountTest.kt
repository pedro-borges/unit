package org.pcb.unit.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
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

class ElectricCurrentAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 * 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 / 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 0 + 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 0,
                                           unit = ELECTRICCURRENT1) +
                     ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun plusOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 0 + 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 0,
                                           unit = ELECTRICCURRENT1) +
                     ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT2))
    }

    @Test
    fun minusSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 1 - 1,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT1) -
                     ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun minusOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 - 2,
                                             unit = ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) -
                     ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT2))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) /
                     ElectricCurrentAmount(amount = 1,
                                           unit = ELECTRICCURRENT2))
    }

    @Test
    fun timesTime()
    {
        assertEquals<ElectricChargeAmount<out Unit<ElectricCurrent>, TIME1>>(
            expected = ElectricChargeAmount(amount = 2 * 2,
                                            unit = ELECTRICCURRENT1 * TIME1),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     TimeAmount(amount = 2,
                                unit = TIME1))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                    (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricResistanceAmount(amount = 2,
                                              unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                     (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, out Unit<Length>, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                    (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricResistanceAmount(amount = 2 * (2 * 2),
                                              unit = (MASS2 * LENGTH2 * LENGTH2) /
                                                     (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2)))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals<PowerAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = (MASS1 * LENGTH1 * LENGTH1) /
                                          (TIME1 * TIME1 * TIME1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricTensionAmount(amount = 2,
                                           unit = (MASS1 * LENGTH1 * LENGTH1) /
                                                  (TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1)))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals<PowerAmount<out Unit<Mass>, out Unit<Length>, out Unit<Time>>>(
            expected = PowerAmount(amount = 2 * 2,
                                   unit = (MASS1 * LENGTH1 * LENGTH1) /
                                          (TIME1 * TIME1 * TIME1)),
            actual = ElectricCurrentAmount(amount = 2,
                                           unit = ELECTRICCURRENT1) *
                     ElectricTensionAmount(amount = 2 * 2,
                                           unit = (MASS2 * LENGTH2 * LENGTH2) /
                                                  (TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2)))
    }
}