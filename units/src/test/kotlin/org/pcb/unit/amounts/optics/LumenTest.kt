package org.pcb.unit.amounts.optics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.LuminousFluxAmount
import org.pcb.unit.amounts.base.Candela
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.optics.Lumen.LUMEN
import org.pcb.unit.amounts.space.SquareMeter
import org.pcb.unit.amounts.trigonometry.Steradian

class LumenTest
{
    private infix fun Lumen.equivalent(other: LuminousFluxAmount<*, *>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Lumen(2) equivalent LuminousFluxAmount(2, U2(RADIAN, CANDELA))
        Lumen(2) equivalent LuminousFluxAmount(2, LUMEN)
    }

    @Test
    fun candelaTimesSteradian() =
        assertEquals(
            expected = Lumen(2 * 3),
            actual = Candela(2) * Steradian(3))

    @Test
    fun luxTimesSquareMeter() =
        assertEquals(
            expected = Lumen(2 * 3),
            actual = Lux(2) * SquareMeter(3))

    @Test
    fun squareMeterTimesLux() =
        assertEquals(
            expected = Lumen(2 * 3),
            actual = SquareMeter(2) * Lux(3))

    @Test
    fun steradianTimesCandela() =
        assertEquals(
            expected = Lumen(2 * 3),
            actual = Steradian(2) * Candela(3))

    @Test
    fun talbotDivSecond() =
        assertEquals(
            expected = Lumen(6 / 3),
            actual = Talbot(6) / Second(3))
}
