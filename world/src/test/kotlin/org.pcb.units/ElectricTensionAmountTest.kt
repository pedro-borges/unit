package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ElectricChargeAmount
import org.pcb.units.amount.ElectricCurrentAmount
import org.pcb.units.amount.ElectricResistanceAmount
import org.pcb.units.amount.ElectricTensionAmount
import org.pcb.units.amount.EnergyAmount
import org.pcb.units.amount.PowerAmount
import org.pcb.units.amount.base.Ampere.AMPERE
import org.pcb.units.amount.base.Kilogram.KILOGRAM
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.amount.electromagnetism.Coulomb.COULOMB
import org.pcb.units.amount.electromagnetism.Ohm.OHM
import org.pcb.units.amount.electromagnetism.Volt.VOLT
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.mechanics.Joule.JOULE
import org.pcb.units.amount.mechanics.Watt.WATT
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.time.Hour.HOUR
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.dimension.Length
import org.pcb.units.dimension.Time

class ElectricTensionAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ElectricTensionAmount<KILOGRAM, out Unit<Length>, out Unit<Time>, AMPERE>>(
            expected = ElectricTensionAmount(1, KILOGRAM, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE),
            actual = ElectricTensionAmount(12 * 60 * 3600, KILOGRAM, FOOT, INCH, SECOND, MINUTE, HOUR, AMPERE))
    }

    @Test
    fun ownUnit()
    {
        assertEquals(
            expected = ElectricTensionAmount(1, KILOGRAM, METER, METER, SECOND, SECOND, SECOND, AMPERE),
            actual = ElectricTensionAmount(1, VOLT))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricTensionAmount(4, VOLT),
            actual = ElectricTensionAmount(2, VOLT) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricTensionAmount(1, VOLT),
            actual = ElectricTensionAmount(2, VOLT) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(1, VOLT),
            actual = ElectricTensionAmount(0, VOLT) +
                     ElectricTensionAmount(1, VOLT))
    }

    @Test
    fun plusOtherElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(1, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE),
            actual = ElectricTensionAmount(0, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE) +
                     ElectricTensionAmount(16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE, AMPERE))
    }

    @Test
    fun minusSameElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(0, VOLT),
            actual = ElectricTensionAmount(1, VOLT) -
                     ElectricTensionAmount(1, VOLT))
    }

    @Test
    fun minusOtherElectricTension()
    {
        assertEquals(
            expected = ElectricTensionAmount(0, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE),
            actual = ElectricTensionAmount(1, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE) -
                     ElectricTensionAmount(16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE, AMPERE))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ElectricTensionAmount(2, VOLT) /
                     ElectricTensionAmount(2, VOLT))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ElectricTensionAmount(2, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE) /
                     ElectricTensionAmount(2 * 16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE, AMPERE))
    }

    @Test
    fun timesSameElectricCharge()
    {
        assertEquals(
            expected = EnergyAmount(4, JOULE),
            actual = ElectricTensionAmount(2, VOLT) *
                     ElectricChargeAmount(2, COULOMB))
    }

    @Test
    fun timesOtherElectricCharge()
    {
        assertEquals(
            expected = EnergyAmount(4, KILOGRAM, METER, METER, MINUTE, MINUTE),
            actual = ElectricTensionAmount(2, KILOGRAM, METER, METER, MINUTE, MINUTE, MINUTE, AMPERE) *
                     ElectricChargeAmount(120, AMPERE, SECOND))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricResistanceAmount(1, OHM),
            actual = ElectricTensionAmount(2, VOLT) /
                     ElectricCurrentAmount(2, AMPERE))
    }

    @Test
    fun divOtherElectricCurrent()
    {
        // No other known electric current unit
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = ElectricTensionAmount(2, VOLT) /
                     ElectricResistanceAmount(2, OHM))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = ElectricTensionAmount(2, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE) /
                     ElectricResistanceAmount(2 * 16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE, AMPERE, AMPERE))
    }


    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = PowerAmount(4, WATT),
            actual = ElectricTensionAmount(2, VOLT) *
                     ElectricCurrentAmount(2, AMPERE))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        // No other known electric current unit
    }
}