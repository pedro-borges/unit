package org.pcb.unit.units.mechanics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.EnergyAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Second

class JouleTest
{
    @Test
    fun unit()
    {
        Joule(1) equivalent Kilogram(1) * (Meter(1) / Second(1)) * (Meter(1) / Second(1))
    }

    private infix fun Joule.equivalent(other: EnergyAmount<*, *, *>) =
        assertEquals(this, Joule(0) + other)
}