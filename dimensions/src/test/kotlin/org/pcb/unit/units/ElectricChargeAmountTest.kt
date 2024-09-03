package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ElectricChargeAmount
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.PercentAmount
import org.pcb.unit.units.test.ELECTRICCURRENT1
import org.pcb.unit.units.test.ELECTRICCURRENT2
import org.pcb.unit.units.test.TIME1
import org.pcb.unit.units.test.TIME2

class ElectricChargeAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricChargeAmount(2 * 2, ELECTRICCURRENT1, TIME1),
            actual = ElectricChargeAmount(2, ELECTRICCURRENT1, TIME1) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricChargeAmount(2 / 2, ELECTRICCURRENT1, TIME1),
            actual = ElectricChargeAmount(2, ELECTRICCURRENT1, TIME1) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(0 + 1, ELECTRICCURRENT1, TIME1),
            actual = ElectricChargeAmount(0, ELECTRICCURRENT1, TIME1) +
                     ElectricChargeAmount(1, ELECTRICCURRENT1, TIME1))
    }

    @Test
    fun plusOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(0 + (2 * 2), ELECTRICCURRENT1, TIME1),
            actual = ElectricChargeAmount(0, ELECTRICCURRENT1, TIME1) +
                     ElectricChargeAmount(1, ELECTRICCURRENT2, TIME2))
    }

    @Test
    fun minusSameElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(1 - 1, ELECTRICCURRENT1, TIME1),
            actual = ElectricChargeAmount(1, ELECTRICCURRENT1, TIME1) -
                     ElectricChargeAmount(1, ELECTRICCURRENT1, TIME1))
    }

    @Test
    fun minusOtherElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(4 - (2 * 2), ELECTRICCURRENT1, TIME1),
            actual = ElectricChargeAmount(4, ELECTRICCURRENT1, TIME1) -
                     ElectricChargeAmount(1, ELECTRICCURRENT2, TIME2))
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ElectricChargeAmount(2, ELECTRICCURRENT1, TIME1) /
                     ElectricChargeAmount(2, ELECTRICCURRENT1, TIME1))
    }

    @Test
    fun divOtherElectricCharge()
    {
        assertEquals(
            expected = NaturalAmount(4 / (2 * 2)),
            actual = ElectricChargeAmount(4, ELECTRICCURRENT1, TIME1) /
                     ElectricChargeAmount(1, ELECTRICCURRENT2, TIME2))
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = ElectricCurrentAmount(2 / 2, ELECTRICCURRENT1),
            actual = ElectricChargeAmount(2, ELECTRICCURRENT1, TIME1) /
                     TimeAmount(2, TIME1))
    }
}