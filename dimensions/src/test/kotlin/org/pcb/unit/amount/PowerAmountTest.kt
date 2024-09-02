package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amount.ElectricCurrentAmount
import org.pcb.unit.amount.ElectricTensionAmount
import org.pcb.unit.amount.EnergyAmount
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

class PowerAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<PowerAmount<MASS1, out Unit<Length>, out Unit<Time>>>(
            expected = PowerAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH2, TIME1, TIME2, TIME2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = PowerAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PowerAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSamePower()
    {
        assertEquals(
            expected = PowerAmount(0 + 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = PowerAmount(0, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) +
                     PowerAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1))
    }

    @Test
    fun plusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(0 + 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = PowerAmount(0, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) +
                     PowerAmount(1, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2))
    }

    @Test
    fun minusSamePower()
    {
        assertEquals(
            expected = PowerAmount(1 - 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = PowerAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) -
                     PowerAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1))
    }

    @Test
    fun minusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(1 - 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = PowerAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) -
                     PowerAmount(1, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2))
    }

    @Test
    fun divSamePower()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) /
                     PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1))
    }

    @Test
    fun divOtherPower()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) /
                     PowerAmount(2, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = EnergyAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) *
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = EnergyAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) *
                     TimeAmount(1, TIME2))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(2 / 2, ELECTRICCURRENT1),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) /
                     ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(2 / 2, ELECTRICCURRENT1),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) /
                     ElectricTensionAmount(2, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT1))
    }

    @Test
    fun divElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = PowerAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1) /
                     ElectricCurrentAmount(2, ELECTRICCURRENT1))
    }
}