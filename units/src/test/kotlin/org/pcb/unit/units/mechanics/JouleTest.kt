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

class JouleTest
{
    @Test
    fun unit()
    {
        Joule(1) equivalent Kilogram(1) * (Meter(1) / Second(1)) * (Meter(1) / Second(1))
    }

    private infix fun Joule.equivalent(other: EnergyAmount<*, *, *>) =
        assertEquals<EnergyAmount<out Unit<Mass>, METER, out Unit<Time>>>(this, Joule(0) + other)
}