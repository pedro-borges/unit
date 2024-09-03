package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Second

class NewtonTest
{
    @Test
    fun unit()
    {
        Newton(1) equivalent Kilogram(1) * (Meter(1) / Second(1)) / Second(1)
    }

    private infix fun Newton.equivalent(other: ForceAmount<*, *, *>) =
        assertEquals(this, Newton(0) + other)
}