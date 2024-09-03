package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.VolumeAmount

class LiterTest
{
    @Test
    fun scales()
    {
        Liter(1000000) equivalent Stere(1)
        Liter(1) equivalent DeciMeter(1) * DeciMeter(1) * DeciMeter(1)
    }

    private infix fun Liter.equivalent(other: VolumeAmount<*>) =
        assertEquals(this, Liter(0) + other)
}
