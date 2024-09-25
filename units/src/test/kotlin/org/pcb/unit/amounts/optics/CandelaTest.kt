package org.pcb.unit.amounts.optics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.LuminousIntensityAmount
import org.pcb.unit.amounts.space.SquareMeter
import org.pcb.unit.amounts.trigonometry.Steradian
import org.pcb.unit.base.Candela
import org.pcb.unit.base.Candela.CANDELA

class CandelaTest
{
    private infix fun Candela.equivalent(other: LuminousIntensityAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Candela(2) equivalent LuminousIntensityAmount(2, CANDELA)
    }

    @Test
    fun lumenDivSteradian() =
        assertEquals(
            expected = Candela(6 / 3),
            actual = Lumen(6) / Steradian(3))

    @Test
    fun nitTimesSquareMeter() =
        assertEquals(
            expected = Candela(2 * 3),
            actual = Nit(2) * SquareMeter(3))

    @Test
    fun squareMeterTimesNit() =
        assertEquals(
            expected = Candela(2 * 3),
            actual = SquareMeter(2) * Nit(3))
}
