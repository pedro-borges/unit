package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Second

class WattTest
{
    @Test
    fun unit()
    {
        Watt(1) equivalent Kilogram(1) * (Meter(1) / Second(1)) * (Meter(1) / Second(1)) / Second(1)
    }

    private infix fun Watt.equivalent(other: PowerAmount<*, *, *>) =
        assertEquals(this, Watt(0) + other)
}