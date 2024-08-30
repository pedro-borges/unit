package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ElectricChargeAmount
import org.pcb.units.amount.ElectricCurrentAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.cannon.Ampere.AMPERE
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount

class ElectricCurrentAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(4, AMPERE),
            actual = ElectricCurrentAmount(2, AMPERE) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = ElectricCurrentAmount(2, AMPERE) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricalCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = ElectricCurrentAmount(0, AMPERE) + ElectricCurrentAmount(1, AMPERE))
    }

    @Test
    fun plusOtherElectricalCurrent()
    {
        // No other known electrical current unit
    }

    @Test
    fun minusSameElectricalCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(0, AMPERE),
            actual = ElectricCurrentAmount(1, AMPERE) - ElectricCurrentAmount(1, AMPERE))
    }

    @Test
    fun minusOtherElectricalCurrent()
    {
        // No other known electrical current unit
    }

    @Test
    fun divSameElectricalCurrent()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ElectricCurrentAmount(2, AMPERE) / ElectricCurrentAmount(2, AMPERE))
    }

    @Test
    fun divOtherElectricalCurrent()
    {
        // No other known electrical current unit
    }

    @Test
    fun timesTime()
    {
        assertEquals(
            expected = ElectricChargeAmount(4, AMPERE, SECOND),
            actual = ElectricCurrentAmount(2, AMPERE) * TimeAmount(2, SECOND))
    }
}