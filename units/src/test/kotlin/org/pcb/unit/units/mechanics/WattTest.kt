package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.mechanics.Watt.WATT

class WattTest
{
    @Test
    fun unit()
    {
        Watt(1) equivalent Kilogram(2) * (Meter(2) / Second(2)) * (Meter(2) / Second(2)) / Second(2)
        Watt(2) equivalent PowerAmount(2, WATT)
    }

    private infix fun Watt.equivalent(other: PowerAmount<*, *, *>) =
        assertEquals<PowerAmount<out Unit<Mass>, METER, out Unit<Time>>>(this, Watt(0) + other)
}