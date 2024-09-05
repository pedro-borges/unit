package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Coulomb
import org.pcb.unit.units.electromagnetism.Volt
import org.pcb.unit.units.electromagnetism.Weber
import org.pcb.unit.units.mechanics.Joule.JOULE

class JouleTest
{
    private infix fun Joule.equivalent(other: EnergyAmount<*, *, *>) =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(this, Joule(0) + other)

    @Test
    fun unit()
    {
        Joule(2) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2))
        Joule(2) equivalent EnergyAmount(2, JOULE)
    }

    @Test
    fun ampereTimesWeber() =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(2 * 3),
            actual = Ampere(2) * Weber(3))

    @Test
    fun coulombTimesVolt() =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(2 * 3),
            actual = Coulomb(2) * Volt(3))

    @Test
    fun meterTimesNewton() =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(2 * 3),
            actual = Meter(2) * Newton(3))

    @Test
    fun newtonTimesMeter() =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(2 * 3),
            actual = Newton(2) * Meter(3))

    @Test
    fun secondTimesWatt() =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(2 * 3),
            actual = Second(2) * Watt(3))

    @Test
    fun voltTimesCoulomb() =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(2 * 3),
            actual = Volt(2) * Coulomb(3))

    @Test
    fun wattTimesSecond() =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(2 * 3),
            actual = Watt(2) * Second(3))

    @Test
    fun weberTimesAmpere() =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(2 * 3),
            actual = Weber(2) * Ampere(3))
}