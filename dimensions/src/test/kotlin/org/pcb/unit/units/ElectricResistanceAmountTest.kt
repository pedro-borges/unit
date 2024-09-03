package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricResistanceAmount
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

class ElectricResistanceAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ElectricResistanceAmount<MASS1, out Unit<Length>, out Unit<Time>, out Unit<ElectricCurrent>>>(
            expected = ElectricResistanceAmount(amount = 1,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 2 * 2,
                                              major = MASS1 * LENGTH1 * LENGTH2,
                                              minor = TIME1 * TIME2 * TIME2 * ELECTRICCURRENT1 * ELECTRICCURRENT2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 2 * 2,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 2,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 2 / 2,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 2,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricResistance()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 0 + 1,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 0,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) +
                     ElectricResistanceAmount(amount = 1,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1))
    }

    @Test
    fun plusOtherElectricResistance()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 0 + 1,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 0,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) +
                     ElectricResistanceAmount(amount = 2 * 2,
                                              major = MASS2 * LENGTH2 * LENGTH2,
                                              minor = TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2))
    }

    @Test
    fun minusSameElectricResistance()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 1 - 1,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 1,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) -
                     ElectricResistanceAmount(amount = 1,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1))
    }

    @Test
    fun minusOtherElectricResistance()
    {
        assertEquals<ElectricResistanceAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricResistanceAmount(amount = 1 - 1,
                                                major = MASS1 * LENGTH1 * LENGTH1,
                                                minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 1,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) -
                     ElectricResistanceAmount(amount = 2 * 2,
                                              major = MASS2 * LENGTH2 * LENGTH2,
                                              minor = TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricResistanceAmount(amount = 2,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                     ElectricResistanceAmount(amount = 2,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricResistanceAmount(amount = 2,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) /
                     ElectricResistanceAmount(amount = 2 * (2 * 2),
                                              major = MASS2 * LENGTH2 * LENGTH2,
                                              minor = TIME2 * TIME2 * TIME2 * ELECTRICCURRENT2 * ELECTRICCURRENT2))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 2,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) *
                     ElectricCurrentAmount(amount = 2,
                                           major = ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 * 2,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(amount = 2,
                                              major = MASS1 * LENGTH1 * LENGTH1,
                                              minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1 * ELECTRICCURRENT1) *
                     ElectricCurrentAmount(amount = 1,
                                           major = ELECTRICCURRENT2))
    }
}