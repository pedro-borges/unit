package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ElectricChargeAmount
import org.pcb.units.amount.ElectricTensionAmount
import org.pcb.units.amount.EnergyAmount
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.electromagnetism.Coulomb.COULOMB
import org.pcb.units.amount.electromagnetism.Volt.VOLT
import org.pcb.units.amount.length.Foot.FOOT
import org.pcb.units.amount.length.Inch.INCH
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.mechanics.Joule.JOULE
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.domain.Length
import org.pcb.units.domain.Time

class EnergyAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<EnergyAmount<KILOGRAM, out Unit<Length>, out Unit<Time>>>(
            expected = EnergyAmount(1, KILOGRAM, FOOT, FOOT, SECOND, SECOND),
            actual = EnergyAmount(12 * 60, KILOGRAM, FOOT, INCH, SECOND, MINUTE))
    }

    @Test
    fun ownUnit()
    {
        assertEquals(
            expected = EnergyAmount(1, KILOGRAM, METER, METER, SECOND, SECOND),
            actual = EnergyAmount(1, JOULE))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = EnergyAmount(4, JOULE),
            actual = EnergyAmount(2, JOULE) * PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = EnergyAmount(1, JOULE),
            actual = EnergyAmount(2, JOULE) / PercentAmount(2 * 100))
    }

    @Test
    fun plusSameEnergy()
    {
        assertEquals(
            expected = EnergyAmount(1, JOULE),
            actual = EnergyAmount(0, JOULE) + EnergyAmount(1, JOULE))
    }

    @Test
    fun plusOtherEnergy()
    {
        assertEquals(
            expected = EnergyAmount(1, POUND, FOOT, FOOT, SECOND, SECOND),
            actual = EnergyAmount(0, POUND, FOOT, FOOT, SECOND, SECOND) + EnergyAmount(16 * 12 * 12 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE))
    }

    @Test
    fun minusSameEnergy()
    {
        assertEquals(
            expected = EnergyAmount(0, JOULE),
            actual = EnergyAmount(1, JOULE) - EnergyAmount(1, JOULE))
    }

    @Test
    fun minusOtherEnergy()
    {
        assertEquals(
            expected = EnergyAmount(0, POUND, FOOT, FOOT, SECOND, SECOND),
            actual = EnergyAmount(1, POUND, FOOT, FOOT, SECOND, SECOND) - EnergyAmount(16 * 12 * 12 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE))
    }

    @Test
    fun divSameEnergy()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = EnergyAmount(2, JOULE) / EnergyAmount(2, JOULE))
    }

    @Test
    fun divOtherEnergy()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = EnergyAmount(2, POUND, FOOT, FOOT, SECOND, SECOND) / EnergyAmount(2 * 16 * 12 * 12 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE))
    }

    @Test
    fun divSameSpace()
    {
        assertEquals(
            expected = ForceAmount(1, KILOGRAM, METER, SECOND, SECOND),
            actual = EnergyAmount(2, KILOGRAM, METER, METER, SECOND, SECOND) / LengthAmount(2, METER))
    }

    @Test
    fun divOtherSpace()
    {
        assertEquals(
            expected = ForceAmount(1, KILOGRAM, FOOT, SECOND, SECOND),
            actual = EnergyAmount(2, KILOGRAM, FOOT, FOOT, SECOND, SECOND) / LengthAmount(2 * 12, INCH))
    }

    @Test
    fun divSameForce()
    {
        assertEquals(
            expected = LengthAmount(1, METER),
            actual = EnergyAmount(2, KILOGRAM, METER, METER, SECOND, SECOND) / ForceAmount(2, KILOGRAM, METER, SECOND, SECOND))
    }

    @Test
    fun divOtherForce()
    {
        assertEquals(
            expected = LengthAmount(1, FOOT),
            actual = EnergyAmount(2, POUND, FOOT, FOOT, SECOND, SECOND) / ForceAmount(2 * 16 * 12 * 60 * 60, OUNCE, INCH, MINUTE, MINUTE))
    }

    @Test
    fun divElectricCharge()
    {
        assertEquals(
            expected = ElectricTensionAmount(1, VOLT),
            actual = EnergyAmount(2, JOULE) / ElectricChargeAmount(2, COULOMB))
    }
}