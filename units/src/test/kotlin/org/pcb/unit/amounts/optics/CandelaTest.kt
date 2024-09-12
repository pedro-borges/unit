package org.pcb.unit.amounts.optics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.LuminousIntensityAmount
import org.pcb.unit.amounts.base.Candela
import org.pcb.unit.amounts.base.Candela.CANDELA

class CandelaTest
{
    private infix fun Candela.equivalent(other: LuminousIntensityAmount<*>) =
        assertEquals(this, Candela(0) + other)

    @Test
    fun scales()
    {
        Candela(2) equivalent LuminousIntensityAmount(2, CANDELA)
    }
}
