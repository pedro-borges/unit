package org.pcb.unit.amounts.optics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.LuminanceAmount
import org.pcb.unit.amounts.base.Candela
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.optics.Nit.NIT
import org.pcb.unit.amounts.space.SquareMeter

class NitTest
{
    private infix fun Nit.equivalent(other: LuminanceAmount<*, *>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Nit(2) equivalent LuminanceAmount(2, CANDELA to METER)
        Nit(2) equivalent LuminanceAmount(2, NIT)
    }

    @Test
    fun candelaDivSquareMeter() =
        assertEquals(
            expected = Nit(6 / 3),
            actual = Candela(6) / SquareMeter(3))
}
