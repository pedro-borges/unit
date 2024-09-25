package org.pcb.unit.amounts.optics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U3
import org.pcb.unit.amounts.LuminousEnergyAmount
import org.pcb.unit.amounts.optics.Talbot.TALBOT
import org.pcb.unit.base.Candela.CANDELA
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.base.Second
import org.pcb.unit.base.Second.SECOND

class TalbotTest
{
    private infix fun Talbot.equivalent(other: LuminousEnergyAmount<*, *, *>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Talbot(2) equivalent LuminousEnergyAmount(2, U3(RADIAN, CANDELA, SECOND))
        Talbot(2) equivalent LuminousEnergyAmount(2, TALBOT)
    }

    @Test
    fun lumenTimesSecond() =
        assertEquals(
            expected = Talbot(2 * 3),
            actual = Lumen(2) * Second(3))

    @Test
    fun secondTimesLumen() =
        assertEquals(
            expected = Talbot(2 * 3),
            actual = Second(2) * Lumen(3))
}
