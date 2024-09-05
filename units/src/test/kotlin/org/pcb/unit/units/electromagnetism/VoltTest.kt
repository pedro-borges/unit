package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ElectricTensionAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Volt.VOLT
import org.pcb.unit.units.mechanics.Joule
import org.pcb.unit.units.mechanics.Watt

class VoltTest
{
    private infix fun Volt.equivalent(other: ElectricTensionAmount<*, *, *, *>) =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(this, Volt(0) + other)

    @Test
    fun unit()
    {
        Volt(0.5) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2) / Ampere(2)
        Volt(2) equivalent ElectricTensionAmount(2, VOLT)
    }

    @Test
    fun ampereTimesOhm() =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Volt(2 * 3),
            actual = Ampere(2) * Ohm(3))

    @Test
    fun ohmTimesAmpere() =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Volt(2 * 3),
            actual = Ohm(2) * Ampere(3))

    @Test
    fun ampereDivSiemens() =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Volt(6 / 3),
            actual = Ampere(6) / Siemens(3))

    @Test
    fun coulombDivFarad() =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Volt(6 / 3),
            actual = Coulomb(6) / Farad(3))

    @Test
    fun jouleDivCoulomb() =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Volt(6 / 3),
            actual = Joule(6) / Coulomb(3))

    @Test
    fun wattDivAmpere() =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Volt(6 / 3),
            actual = Watt(6) / Ampere(3))

    @Test
    fun weberDivSecond() =
        assertEquals<ElectricTensionAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Volt(6 / 3),
            actual = Weber(6) / Second(3))
}