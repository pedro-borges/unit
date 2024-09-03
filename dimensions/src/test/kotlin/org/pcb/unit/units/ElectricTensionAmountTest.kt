package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.PowerAmount
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

class ElectricTensionAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ElectricTensionAmount<MASS1, out Unit<Length>, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 1,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH2,
                                           minor = TIME1 * TIME2 * TIME2 * ELECTRICCURRENT1))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 / 2,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricTension()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 0 + 1,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 0,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) +
                     ElectricTensionAmount(amount = 1,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1))
    }

    @Test
    fun plusOtherElectricTension()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 0 + 1,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 0,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) +
                     ElectricTensionAmount(amount = 2,
                                           major = MASS2 * LENGTH2 * LENGTH2,
                                           minor = TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2))
    }

    @Test
    fun minusSameElectricTension()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 1 - 1,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 1,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) -
                     ElectricTensionAmount(amount = 1,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1))
    }

    @Test
    fun minusOtherElectricTension()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 1 - 1,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 1,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) -
                     ElectricTensionAmount(amount = 2,
                                           major = MASS2 * LENGTH2 * LENGTH2,
                                           minor = TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) /
                     ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) /
                     ElectricTensionAmount(amount = 2 * 2,
                                           major = MASS2 * LENGTH2 * LENGTH2,
                                           minor = TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2))
    }

    @Test
    fun timesSameElectricCharge()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) *
                     ElectricChargeAmount(amount = 2,
                                          major = ELECTRICCURRENT1 * TIME1))
    }

    @Test
    fun timesOtherElectricCharge()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * (2 * 2),
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) *
                     ElectricChargeAmount(amount = 1,
                                          major = ELECTRICCURRENT2 * TIME2))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 2 / 2,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) /
                     ElectricCurrentAmount(amount = 2,
                                           major = ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 2 / 2,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) /
                     ElectricCurrentAmount(amount = 1,
                                           major = ELECTRICCURRENT2))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 / 2,
                                             major = ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) /
                     ElectricResistanceAmount(amount = 2,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 / 2,
                                             major = ELECTRICCURRENT1),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) /
                     ElectricResistanceAmount(amount = 2 * (2 * 2),
                                              major = MASS2 * LENGTH2 * LENGTH2,
                                              minor = TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2))
    }


    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = PowerAmount(2 * 2, (MASS1 * LENGTH1 * LENGTH1) to (TIME1 * TIME1 * TIME1)),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) *
                     ElectricCurrentAmount(amount = 2,
                                           major = ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = PowerAmount(2 * 2, (MASS1 * LENGTH1 * LENGTH1) to (TIME1 * TIME1 * TIME1)),
            actual = ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1) *
                     ElectricCurrentAmount(amount = 1,
                                           major = ELECTRICCURRENT2))
    }
}