package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter

class StereTest
{
    @Test
    fun scales()
    {
        Stere(1) equivalent Liter(1000000)
        Stere(1) equivalent Meter(1) * Meter(1) * Meter(1)
    }

    private infix fun Stere.equivalent(other: VolumeAmount<*>) =
        assertEquals(this, Stere(0) + other)
}
