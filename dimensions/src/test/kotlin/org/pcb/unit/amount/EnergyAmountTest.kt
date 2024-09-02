package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amount.ElectricChargeAmount
import org.pcb.unit.amount.ElectricTensionAmount
import org.pcb.unit.amount.EnergyAmount
import org.pcb.unit.amount.ForceAmount
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.PowerAmount
import org.pcb.unit.amount.TimeAmount
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.test.ELECTRICCURRENT1
import org.pcb.unit.amount.test.LENGTH1
import org.pcb.unit.amount.test.LENGTH2
import org.pcb.unit.amount.test.MASS1
import org.pcb.unit.amount.test.MASS2
import org.pcb.unit.amount.test.TIME1
import org.pcb.unit.amount.test.TIME2
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Time

class EnergyAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<EnergyAmount<MASS1, out Unit<Length>, out Unit<Time>>>(
            expected = EnergyAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(1, MASS1, LENGTH1, LENGTH2, TIME1, TIME2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = EnergyAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = EnergyAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameEnergy()
    {
        assertEquals(
            expected = EnergyAmount(0 + 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(0, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) +
                     EnergyAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun plusOtherEnergy()
    {
        assertEquals(
            expected = EnergyAmount(0 + 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(0, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) +
                     EnergyAmount(1, MASS2, LENGTH2, LENGTH2, TIME2, TIME2))
    }

    @Test
    fun minusSameEnergy()
    {
        assertEquals(
            expected = EnergyAmount(1 - 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) -
                     EnergyAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun minusOtherEnergy()
    {
        assertEquals(
            expected = EnergyAmount(2 - 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) -
                     EnergyAmount(1, MASS2, LENGTH2, LENGTH2, TIME2, TIME2))
    }

    @Test
    fun divSameEnergy()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun divOtherEnergy()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     EnergyAmount(1, MASS2, LENGTH2, LENGTH2, TIME2, TIME2))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = ForceAmount(2 / 2, MASS1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     LengthAmount(2, LENGTH1))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = ForceAmount(2 / 2, MASS1, LENGTH1, TIME1, TIME1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     LengthAmount(1, LENGTH2))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = LengthAmount(2 / 2, LENGTH1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     ForceAmount(2, MASS1, LENGTH1, TIME1, TIME1))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = LengthAmount(2 / 2, LENGTH1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     ForceAmount(2, MASS2, LENGTH2, TIME2, TIME2))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = PowerAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     TimeAmount(2, TIME1))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = PowerAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = ElectricTensionAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     ElectricChargeAmount(2, ELECTRICCURRENT1, TIME1))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricTensionAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = EnergyAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1) /
                     ElectricChargeAmount(1, ELECTRICCURRENT1, TIME2))
    }
}