package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amount.ElectricCurrentAmount
import org.pcb.unit.amount.ElectricResistanceAmount
import org.pcb.unit.amount.ElectricTensionAmount
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
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Time

class ElectricResistanceAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ElectricResistanceAmount<MASS1, out Unit<Length>, out Unit<Time>, out Unit<ElectricCurrent>>>(
            expected = ElectricResistanceAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(2 * 2, MASS1, LENGTH1, LENGTH2, TIME1, TIME2, TIME2, ELECTRICCURRENT1, ELECTRICCURRENT2))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricResistanceAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricResistanceAmount(2 / 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(0 + 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(0, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) +
                     ElectricResistanceAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1))
    }

    @Test
    fun plusOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(0 + 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(0, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) +
                     ElectricResistanceAmount(2 * 2, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2, ELECTRICCURRENT2))
    }

    @Test
    fun minusSameElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(1 - 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) -
                     ElectricResistanceAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1))
    }

    @Test
    fun minusOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(1 - 1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(1, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) -
                     ElectricResistanceAmount(2 * 2, MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2, ELECTRICCURRENT2))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) /
                     ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = NaturalAmount(2 / 2),
            actual = ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) /
                     ElectricResistanceAmount(2 * (2 * 2), MASS2, LENGTH2, LENGTH2, TIME2, TIME2, TIME2, ELECTRICCURRENT2, ELECTRICCURRENT2))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) *
                     ElectricCurrentAmount(2, ELECTRICCURRENT1))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(2 * 2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1),
            actual = ElectricResistanceAmount(2, MASS1, LENGTH1, LENGTH1, TIME1, TIME1, TIME1, ELECTRICCURRENT1, ELECTRICCURRENT1) *
                     ElectricCurrentAmount(1, ELECTRICCURRENT2))
    }
}