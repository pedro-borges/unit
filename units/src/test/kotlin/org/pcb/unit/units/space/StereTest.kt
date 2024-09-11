package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.Magnitude.MEGA
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.mechanics.Joule
import org.pcb.unit.units.space.Stere.STERE
import org.pcb.unit.units.thermodynamics.Pascal

class StereTest
{
    private infix fun Stere.equivalent(other: VolumeAmount<*>) =
        assertEquals(this, Stere(0) + other)

    @Test
    fun scales()
    {
        Stere(1) equivalent Liter(1000)
        Stere(1) equivalent Meter(1) * Meter(1) * Meter(1)
        Stere(1) equivalent VolumeAmount(1, METER)
        Stere(1) equivalent VolumeAmount(1, STERE)

        Stere(1000) equivalent Liter(1000000)
        Stere(1, KILO) equivalent Liter(1000000)
        Stere(1, KILO) equivalent Liter(1000, KILO)
        Stere(1, KILO) equivalent Liter(1, MEGA)
    }

    @Test
    fun jouleDivPascal() =
        assertEquals(
            expected = Stere(6 / 3),
            actual = Joule(6) / Pascal(3))
}
