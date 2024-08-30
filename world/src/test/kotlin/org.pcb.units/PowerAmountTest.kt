package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ElectricCurrentAmount
import org.pcb.units.amount.ElectricTensionAmount
import org.pcb.units.amount.EnergyAmount
import org.pcb.units.amount.PowerAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.cannon.Ampere.AMPERE
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.electromagnetism.Volt.VOLT
import org.pcb.units.amount.length.Foot.FOOT
import org.pcb.units.amount.length.Inch.INCH
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.mechanics.Joule.JOULE
import org.pcb.units.amount.mechanics.Watt.WATT
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.time.Hour.HOUR
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.domain.Length
import org.pcb.units.domain.Time

class PowerAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<PowerAmount<KILOGRAM, out Unit<Length>, out Unit<Time>>>(
            expected = PowerAmount(1, KILOGRAM, FOOT, FOOT, SECOND, SECOND, SECOND),
            actual = PowerAmount(12 * 60 * 3600, KILOGRAM, FOOT, INCH, SECOND, MINUTE, HOUR))
    }

    @Test
    fun ownUnit()
    {
        assertEquals(
            expected = PowerAmount(1, KILOGRAM, METER, METER, SECOND, SECOND, SECOND),
            actual = PowerAmount(1, WATT))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = PowerAmount(4, WATT),
            actual = PowerAmount(2, WATT) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = PowerAmount(1, WATT),
            actual = PowerAmount(2, WATT) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSamePower()
    {
        assertEquals(
            expected = PowerAmount(1, WATT),
            actual = PowerAmount(0, WATT) +
                     PowerAmount(1, WATT))
    }

    @Test
    fun plusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(1, POUND, FOOT, FOOT, SECOND, SECOND, SECOND),
            actual = PowerAmount(0, POUND, FOOT, FOOT, SECOND, SECOND, SECOND) +
                     PowerAmount(16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE))
    }

    @Test
    fun minusSamePower()
    {
        assertEquals(
            expected = PowerAmount(0, WATT),
            actual = PowerAmount(1, WATT) -
                     PowerAmount(1, WATT))
    }

    @Test
    fun minusOtherPower()
    {
        assertEquals(
            expected = PowerAmount(0, POUND, FOOT, FOOT, SECOND, SECOND, SECOND),
            actual = PowerAmount(1, POUND, FOOT, FOOT, SECOND, SECOND, SECOND) -
                     PowerAmount(16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE))
    }

    @Test
    fun divSamePower()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = PowerAmount(2, WATT) /
                     PowerAmount(2, WATT))
    }

    @Test
    fun divOtherPower()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = PowerAmount(2, POUND, FOOT, FOOT, SECOND, SECOND, SECOND) /
                     PowerAmount(2 * 16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = EnergyAmount(4, JOULE),
            actual = PowerAmount(2, WATT) *
                     TimeAmount(2, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = EnergyAmount(4, POUND, FOOT, FOOT, SECOND, SECOND),
            actual = PowerAmount(2 * 60, POUND, FOOT, FOOT, SECOND, SECOND, SECOND) *
                     TimeAmount(2, MINUTE))
    }

    @Test
    fun divSameElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = PowerAmount(2, WATT) /
                     ElectricTensionAmount(2, VOLT))
    }

    @Test
    fun divOtherElectricTension()
    {
        assertEquals(
            expected = ElectricCurrentAmount(1, AMPERE),
            actual = PowerAmount(2, POUND, FOOT, FOOT, SECOND, SECOND, SECOND) /
                     ElectricTensionAmount(2 * 16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE, AMPERE))
    }

    @Test
    fun divSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(1, VOLT),
            actual = PowerAmount(2, WATT) /
                     ElectricCurrentAmount(2, AMPERE))
    }
}