package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.mechanics.Newton.NEWTON

class NewtonTest
{
    @Test
    fun unit()
    {
        Newton(1) equivalent Kilogram(2) * (Meter(2) / Second(2)) / Second(2)
        Newton(2) equivalent ForceAmount(2, NEWTON)
    }

    private infix fun Newton.equivalent(other: ForceAmount<*, *, *>) =
        assertEquals<ForceAmount<out Unit<Mass>, METER, out Unit<Time>>>(this, Newton(0) + other)
}