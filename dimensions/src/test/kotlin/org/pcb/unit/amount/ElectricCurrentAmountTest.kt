package org.pcb.unit.amount

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.amount.scalar.PercentAmount
import org.pcb.unit.amount.test.ELECTRICCURRENT1
import org.pcb.unit.amount.test.ELECTRICCURRENT2
import org.pcb.unit.amount.test.LENGTH1
import org.pcb.unit.amount.test.LENGTH2
import org.pcb.unit.amount.test.MASS1
import org.pcb.unit.amount.test.MASS2
import org.pcb.unit.amount.test.TIME1
import org.pcb.unit.amount.test.TIME2
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time

class ElectricCurrentAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(2 * 2, ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(2 / 2, ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(0 + 1, ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(0, ELECTRICCURRENT1) +
                     ElectricCurrentAmount(1, ELECTRICCURRENT1))
    }

    @Test
    fun plusOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(0 + 2, ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(0, ELECTRICCURRENT1) +
                     ElectricCurrentAmount(1, ELECTRICCURRENT2))
    }

    @Test
    fun minusSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1 - 1, ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(1, ELECTRICCURRENT1) -
                     ElectricCurrentAmount(1, ELECTRICCURRENT1))
    }

    @Test
    fun minusOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(2 - 2, ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) -
                     ElectricCurrentAmount(1, ELECTRICCURRENT2))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) /
                     ElectricCurrentAmount(2, ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) /
                     ElectricCurrentAmount(1, ELECTRICCURRENT2))
    }

    @Test
    fun timesTime()
    {
        assertEquals(
            expected = ElectricChargeAmount(2 * 2, ELECTRICCURRENT1, TIME1),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) *
                     TimeAmount(2, TIME1))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = ElectricTensionAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) *
                     ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        assertEquals<ElectricTensionAmount<out Unit<Mass>, out Unit<Length>, out Unit<Time>, ELECTRICCURRENT1>>(
            expected = ElectricTensionAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) *
                     ElectricResistanceAmount(2 * (2 * 2), MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2, ELECTRICCURRENT2))
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = PowerAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) *
                     ElectricTensionAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricTension()
    {
        assertEquals<PowerAmount<out Unit<Mass>, out Unit<Length>, out Unit<Time>>>(
            expected = PowerAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1),
            actual = ElectricCurrentAmount(2, ELECTRICCURRENT1) *
                     ElectricTensionAmount(2 * 2, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2))
    }
}