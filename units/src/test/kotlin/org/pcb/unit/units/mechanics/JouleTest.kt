package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Coulomb
import org.pcb.unit.units.electromagnetism.Volt
import org.pcb.unit.units.mechanics.Joule.JOULE

class JouleTest
{
    @Test
    fun unit()
    {
        Joule(2) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2))
        Joule(2) equivalent EnergyAmount(2, JOULE)

        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(
            expected = Joule(amount = 2 * 3),
            actual = Volt(2) * Coulomb(3))
    }

    private infix fun Joule.equivalent(other: EnergyAmount<*, *, *>) =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(this, Joule(0) + other)
}