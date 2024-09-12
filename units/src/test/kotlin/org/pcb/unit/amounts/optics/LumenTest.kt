package org.pcb.unit.amounts.optics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.LuminousFluxAmount
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.optics.Lumen.LUMEN

class LumenTest
{
    private infix fun Lumen.equivalent(other: LuminousFluxAmount<*, *>) =
        assertEquals(this, Lumen(0) + other)

    @Test
    fun scales()
    {
        Lumen(2) equivalent LuminousFluxAmount(2, U2(CANDELA, RADIAN))
        Lumen(2) equivalent LuminousFluxAmount(2, LUMEN)
    }
}
