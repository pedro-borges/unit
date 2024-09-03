package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.dimension.Length
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
            expected = ElectricTensionAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH2, TIME1, TIME2, TIME2, ELECTRICCURRENT1))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricTensionAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricTensionAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(0 + 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(0, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) +
                     ElectricTensionAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1))
    }

    @Test
    fun plusOtherElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(0 + 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(0, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) +
                     ElectricTensionAmount(2, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2))
    }

    @Test
    fun minusSameElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(1 - 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) -
                     ElectricTensionAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1))
    }

    @Test
    fun minusOtherElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(1 - 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) -
                     ElectricTensionAmount(2, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) /
                     ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) /
                     ElectricTensionAmount(2 * 2, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2))
    }

    @Test
    fun timesSameElectricCharge()
    {
        assertEquals(
            expected = EnergyAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) *
                     ElectricChargeAmount(2, ELECTRICCURRENT1, TIME1))
    }

    @Test
    fun timesOtherElectricCharge()
    {
        assertEquals(
            expected = EnergyAmount(2 * (2 * 2), MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) *
                     ElectricChargeAmount(1, ELECTRICCURRENT2, TIME2))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricResistanceAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) /
                     ElectricCurrentAmount(2, ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricResistanceAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) /
                     ElectricCurrentAmount(1, ELECTRICCURRENT2))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(2 / 2, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) /
                     ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(2 / 2, ELECTRICCURRENT1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) /
                     ElectricResistanceAmount(2 * (2 * 2), MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2, ELECTRICCURRENT2))
    }


    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = PowerAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) *
                     ElectricCurrentAmount(2, ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = PowerAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1) *
                     ElectricCurrentAmount(1, ELECTRICCURRENT2))
    }
}