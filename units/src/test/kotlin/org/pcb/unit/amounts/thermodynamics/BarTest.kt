package org.pcb.unit.amounts.thermodynamics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.EnergyDensityAmount
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.amounts.mechanics.Joule
import org.pcb.unit.amounts.mechanics.Newton
import org.pcb.unit.amounts.space.SquareMeter
import org.pcb.unit.amounts.space.Stere
import org.pcb.unit.amounts.thermodynamics.Bar.BAR
import org.pcb.unit.kilo

class BarTest
{
    private infix fun Bar.equivalent(other: EnergyDensityAmount<*, *, *>) =
        assertEquals(this, other)

    @Test
    fun unit()
    {
        Bar(2) equivalent EnergyDensityAmount(200000, kilo(GRAM) to U2(METER, SECOND))
        Bar(2) equivalent EnergyDensityAmount(2, BAR)
        Bar(2) equivalent Pascal(2 * 100000)
    }

    @Test
    fun newtonDivSquareMeter() =
        assertEquals(
            expected = Bar(6 / 3),
            actual = Newton(6 * 100000) / SquareMeter(3))

    @Test
    fun jouleDivStere() =
        assertEquals(
            expected = Bar(6 / 3),
            actual = Joule(6 * 100000) / Stere(3))
}
