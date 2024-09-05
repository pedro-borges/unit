package org.pcb.unit.units.electromagnetism

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.MagneticFluxAmount
import org.pcb.unit.units.base.Ampere
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Weber.WEBER

class WeberTest
{
    private infix fun Weber.equivalent(other: MagneticFluxAmount<*, *, *, *>) =
        assertEquals<MagneticFluxAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(this, Weber(0) + other)

    @Test
    fun unit()
    {
        Weber(1) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Ampere(2)
        Weber(2) equivalent MagneticFluxAmount(2, WEBER)
    }

    @Test
    fun coulombTimesOhm() =
        assertEquals<MagneticFluxAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Weber(2 * 3),
            actual = Coulomb(2) * Ohm(3))

    @Test
    fun ohmTimesCoulomb() =
        assertEquals<MagneticFluxAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Weber(2 * 3),
            actual = Ohm(2) * Coulomb(3))

    @Test
    fun secondTimesVolt() =
        assertEquals<MagneticFluxAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Weber(2 * 3),
            actual = Second(2) * Volt(3))

    @Test
    fun voltTimesSecond() =
        assertEquals<MagneticFluxAmount<out Unit<Mass>, METER, out Unit<Time>, AMPERE>>(
            expected = Weber(2 * 3),
            actual = Volt(2) * Second(3))
}