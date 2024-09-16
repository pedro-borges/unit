package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.DECI
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.SIScale.MEGA
import org.pcb.unit.amounts.VolumeAmount
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.space.Liter.LITER
import org.pcb.unit.deci

class LiterTest
{
    private infix fun Liter.equivalent(other: VolumeAmount<*>) =
        assertEquals(this, Liter(0) + other)

    @Test
    fun scales()
    {
        Liter(1, KILO) equivalent Stere(1)
        Liter(1) equivalent Meter(1, DECI) * Meter(1, DECI) * Meter(1, DECI)
        Liter(1) equivalent VolumeAmount(1, deci(METER))
        Liter(1) equivalent VolumeAmount(1, LITER)

        Liter(1000) equivalent Stere(1)
        Liter(1, KILO) equivalent Stere(1)
        Liter(1, MEGA) equivalent Stere(1, KILO)
    }
}
