package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ElectricChargeAmount
import org.pcb.units.amount.ElectricCurrentAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.base.Ampere.AMPERE
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.amount.electromagnetism.Coulomb.COULOMB
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount

class ElectricChargeAmountTest
{
    @Test
    fun ownUnit()
    {
        assertEquals(
            expected = ElectricChargeAmount(1, AMPERE, SECOND),
            actual = ElectricChargeAmount(1, COULOMB))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricChargeAmount(4, AMPERE, SECOND),
            actual = ElectricChargeAmount(2, AMPERE, SECOND) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricChargeAmount(1, AMPERE, SECOND),
            actual = ElectricChargeAmount(2, AMPERE, SECOND) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(1, AMPERE, SECOND),
            actual = ElectricChargeAmount(0, AMPERE, SECOND) +
                     ElectricChargeAmount(1, AMPERE, SECOND))
    }

    @Test
    fun plusOtherElectricCharge()
    {
        // No other known electric current unit
    }

    @Test
    fun minusSameElectricCharge()
    {
        assertEquals(
            expected = ElectricChargeAmount(0, AMPERE, SECOND),
            actual = ElectricChargeAmount(1, AMPERE, SECOND) -
                     ElectricChargeAmount(1, AMPERE, SECOND))
    }

    @Test
    fun minusOtherElectricCharge()
    {
        // No other known electric current unit
    }

    @Test
    fun divSameElectricCharge()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ElectricChargeAmount(2, AMPERE, SECOND) /
                     ElectricChargeAmount(2, AMPERE, SECOND))
    }

    @Test
    fun divOtherElectricCharge()
    {
        // No other known electric current unit
    }

    @Test
    fun divTime()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = ElectricChargeAmount(2, AMPERE, SECOND) /
                     TimeAmount(2, SECOND))
    }
}