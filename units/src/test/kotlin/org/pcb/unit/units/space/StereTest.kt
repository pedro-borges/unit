package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.space.Stere.STERE

class StereTest
{
    private infix fun Stere.equivalent(other: VolumeAmount<*>) =
        assertEquals(this, Stere(0) + other)

    @Test
    fun scales()
    {
        Stere(1) equivalent Liter(1000)
        Stere(1) equivalent Meter(1) * Meter(1) * Meter(1)
        Stere(1) equivalent VolumeAmount(1, STERE)
    }
}
