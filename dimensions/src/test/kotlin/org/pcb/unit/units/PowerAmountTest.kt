package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.LENGTH1
import org.pcb.unit.units.test.LENGTH2
import org.pcb.unit.units.test.MASS1
import org.pcb.unit.units.test.MASS2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class PowerAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<PowerAmount<MASS1, out Unit<Length>, out Unit<Time>>>(
            expected = PowerAmount(amount = 1,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH2,
                                 minor = TIME1 * TIME2 * TIME2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals<PowerAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = PowerAmount(amount = 2 * 2,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) *
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals<PowerAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = PowerAmount(amount = 2 / 2,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) /
                     PercentAmount(amount = 2 * 100))
    }

    @Test
    fun plusSamePower()
    {
        assertEquals<PowerAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = PowerAmount(amount = 0 + 1,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = PowerAmount(amount = 0,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) +
                     PowerAmount(amount = 1,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1))
    }

    @Test
    fun plusOtherPower()
    {
        assertEquals<PowerAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = PowerAmount(amount = 0 + 1,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = PowerAmount(amount = 0,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) +
                     PowerAmount(amount = 1,
                                 major = MASS2 * LENGTH2 * LENGTH2,
                                 minor = TIME2 * TIME2 * TIME2))
    }

    @Test
    fun minusSamePower()
    {
        assertEquals<PowerAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = PowerAmount(amount = 1 - 1,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = PowerAmount(amount = 1,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) -
                     PowerAmount(amount = 1,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1))
    }

    @Test
    fun minusOtherPower()
    {
        assertEquals<PowerAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = PowerAmount(amount = 1 - 1,
                                   major = MASS1 * LENGTH1 * LENGTH1,
                                   minor = TIME1 * TIME1 * TIME1),
            actual = PowerAmount(amount = 1,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) -
                     PowerAmount(amount = 1,
                                 major = MASS2 * LENGTH2 * LENGTH2,
                                 minor = TIME2 * TIME2 * TIME2))
    }

    @Test
    fun divSamePower()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) /
                     PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1))
    }

    @Test
    fun divOtherPower()
    {
        assertEquals(
            expected = NaturalAmount(amount = 2 / 2),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) /
                     PowerAmount(amount = 2,
                                 major = MASS2 * LENGTH2 * LENGTH2,
                                 minor = TIME2 * TIME2 * TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) *
                     TimeAmount(amount = 2,
                                major = TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals<EnergyAmount<out Unit<Mass>, LENGTH1, out Unit<Time>>>(
            expected = EnergyAmount(amount = 2 * 2,
                                    major = MASS1 * LENGTH1 * LENGTH1,
                                    minor = TIME1 * TIME1),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) *
                     TimeAmount(amount = 1,
                                major = TIME2))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 / 2,
                                             major = ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           major = MASS1 * LENGTH1 * LENGTH1,
                                           minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(amount = 2 / 2,
                                             major = ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) /
                     ElectricTensionAmount(amount = 2,
                                           major = MASS2 * LENGTH2 * LENGTH2,
                                           minor = TIME2 * TIME2 * TIME2 * ELECTRICCURRENT1))
    }

    @Test
    fun divElectricCurrent()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, LENGTH1, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(amount = 2 / 2,
                                             major = MASS1 * LENGTH1 * LENGTH1,
                                             minor = TIME1 * TIME1 * TIME1 * ELECTRICCURRENT1),
            actual = PowerAmount(amount = 2,
                                 major = MASS1 * LENGTH1 * LENGTH1,
                                 minor = TIME1 * TIME1 * TIME1) /
                     ElectricCurrentAmount(amount = 2,
                                           major = ELECTRICCURRENT1))
    }
}