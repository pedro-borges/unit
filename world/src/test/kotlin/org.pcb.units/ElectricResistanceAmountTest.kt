package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ElectricCurrentAmount
import org.pcb.units.amount.ElectricResistanceAmount
import org.pcb.units.amount.ElectricTensionAmount
import org.pcb.units.amount.base.Ampere.AMPERE
import org.pcb.units.amount.base.Kilogram.KILOGRAM
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.amount.electromagnetism.Ohm.OHM
import org.pcb.units.amount.electromagnetism.Volt.VOLT
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.scalar.PercentAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.time.Hour.HOUR
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.dimension.Length
import org.pcb.units.dimension.Time

class ElectricResistanceAmountTest
{
    @Test
    fun construct()
    {
        assertEquals<ElectricResistanceAmount<KILOGRAM, out Unit<Length>, out Unit<Time>, AMPERE>>(
            expected = ElectricResistanceAmount(1, KILOGRAM, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE, AMPERE),
            actual = ElectricResistanceAmount(12 * 60 * 3600, KILOGRAM, FOOT, INCH, SECOND, MINUTE, HOUR, AMPERE, AMPERE))
    }

    @Test
    fun ownUnit()
    {
        assertEquals(
            expected = ElectricResistanceAmount(1, KILOGRAM, METER, METER, SECOND, SECOND, SECOND, AMPERE, AMPERE),
            actual = ElectricResistanceAmount(1, OHM))
    }

    @Test
    fun timesScalar()
    {
        assertEquals(
            expected = ElectricResistanceAmount(4, OHM),
            actual = ElectricResistanceAmount(2, OHM) *
                     PercentAmount(2 * 100))
    }

    @Test
    fun divScalar()
    {
        assertEquals(
            expected = ElectricResistanceAmount(1, OHM),
            actual = ElectricResistanceAmount(2, OHM) /
                     PercentAmount(2 * 100))
    }

    @Test
    fun plusSameElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(1, OHM),
            actual = ElectricResistanceAmount(0, OHM) +
                     ElectricResistanceAmount(1, OHM))
    }

    @Test
    fun plusOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(1, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE, AMPERE),
            actual = ElectricResistanceAmount(0, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE, AMPERE) +
                     ElectricResistanceAmount(16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE, AMPERE, AMPERE))
    }

    @Test
    fun minusSameElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(0, OHM),
            actual = ElectricResistanceAmount(1, OHM) -
                     ElectricResistanceAmount(1, OHM))
    }

    @Test
    fun minusOtherElectricResistance()
    {
        assertEquals(
            expected = ElectricResistanceAmount(0, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE, AMPERE),
            actual = ElectricResistanceAmount(1, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE, AMPERE) -
                     ElectricResistanceAmount(16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE, AMPERE, AMPERE))
    }

    @Test
    fun divSameElectricResistance()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ElectricResistanceAmount(2, OHM) /
                     ElectricResistanceAmount(2, OHM))
    }

    @Test
    fun divOtherElectricResistance()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = ElectricResistanceAmount(2, POUND, FOOT, FOOT, SECOND, SECOND, SECOND, AMPERE, AMPERE) /
                     ElectricResistanceAmount(2 * 16 * 12 * 12 * 60 * 60 * 60, OUNCE, INCH, INCH, MINUTE, MINUTE, MINUTE, AMPERE, AMPERE))
    }

    @Test
    fun timesSameElectricCurrent()
    {
        assertEquals(
            expected = ElectricTensionAmount(4, VOLT),
            actual = ElectricResistanceAmount(2, OHM) *
                     ElectricCurrentAmount(2, AMPERE))
    }

    @Test
    fun timesOtherElectricCurrent()
    {
        // No other known electric current unit
    }
}