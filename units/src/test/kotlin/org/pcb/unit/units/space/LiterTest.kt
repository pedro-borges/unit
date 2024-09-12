package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.DECI
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.Magnitude.MEGA
import org.pcb.unit.deci
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.space.Liter.LITER

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
