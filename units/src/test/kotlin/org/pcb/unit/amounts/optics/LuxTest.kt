package org.pcb.unit.amounts.optics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.IlluminanceAmount
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.optics.Lux.LUX
import org.pcb.unit.amounts.space.SquareMeter

class LuxTest
{
    private infix fun Lux.equivalent(other: IlluminanceAmount<*, *, *>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Lux(2) equivalent IlluminanceAmount(2, U2(CANDELA, RADIAN) to METER)
        Lux(2) equivalent IlluminanceAmount(2, LUX)
    }

    @Test
    fun lumenDivSquareMeter() =
        assertEquals(
            expected = Lux(6 / 3),
            actual = Lumen(6) / SquareMeter(3))
}
