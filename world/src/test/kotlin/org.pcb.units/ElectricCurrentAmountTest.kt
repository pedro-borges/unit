package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ElectricChargeAmount
import org.pcb.units.amount.ElectricCurrentAmount
import org.pcb.units.amount.ElectricResistanceAmount
import org.pcb.units.amount.ElectricTensionAmount
import org.pcb.units.amount.PowerAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.cannon.Ampere.AMPERE
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.electromagnetism.Ohm.OHM
import org.pcb.units.amount.electromagnetism.Volt.VOLT
import org.pcb.units.amount.mechanics.Watt
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount

class ElectricCurrentAmountTest
{
    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(4, AMPERE),
            actual = ElectricCurrentAmount(2, AMPERE) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = ElectricCurrentAmount(2, AMPERE) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = ElectricCurrentAmount(0, AMPERE) +
                     ElectricCurrentAmount(1, AMPERE))
    }

    @Test
    fun plusOtherElectricCurrent()
    {
        // No other known electric current unit
    }

    @Test
    fun minusSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricCurrentAmount(0, AMPERE),
            actual = ElectricCurrentAmount(1, AMPERE) -
                     ElectricCurrentAmount(1, AMPERE))
    }

    @Test
    fun minusOtherElectricCurrent()
    {
        // No other known electric current unit
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ElectricCurrentAmount(2, AMPERE) /
                     ElectricCurrentAmount(2, AMPERE))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        // No other known electric current unit
    }

    @Test
    fun timesTime()
    {
        assertEquals(
            expected = ElectricChargeAmount(4, AMPERE, SECOND),
            actual = ElectricCurrentAmount(2, AMPERE) *
                     TimeAmount(2, SECOND))
    }

    @Test
    fun timesSameElectricResistance()
    {
        assertEquals(
            expected = ElectricTensionAmount(4, VOLT),
            actual = ElectricCurrentAmount(2, AMPERE) *
                     ElectricResistanceAmount(2, OHM))
    }

    @Test
    fun timesOtherElectricResistance()
    {
        // No other known electric current unit
    }

    @Test
    fun timesSameElectricTension()
    {
        assertEquals(
            expected = PowerAmount(4, Watt.WATT),
            actual = ElectricCurrentAmount(2, AMPERE) *
                     ElectricTensionAmount(2, VOLT))
    }

    @Test
    fun timesOtherElectricTension()
    {
        // No other known electric current unit
    }
}