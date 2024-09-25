package org.pcb.unit.amounts.thermodynamics

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.U2
import org.pcb.unit.amounts.EnergyDensityAmount
import org.pcb.unit.amounts.mechanics.Joule
import org.pcb.unit.amounts.mechanics.Newton
import org.pcb.unit.amounts.space.SquareMeter
import org.pcb.unit.amounts.space.Stere
import org.pcb.unit.amounts.thermodynamics.Pascal.PASCAL
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.kilo

class PascalTest
{
    private infix fun Pascal.equivalent(other: EnergyDensityAmount<*, *, *>) =
        assertEquals(this, other)

    @Test
    fun unit()
    {
        Pascal(2) equivalent EnergyDensityAmount(2, kilo(GRAM) to U2(METER, SECOND))
        Pascal(2) equivalent EnergyDensityAmount(2, PASCAL)
        Pascal(2 * 100000) equivalent Bar(2)
    }

    @Test
    fun newtonDivSquareMeter() =
        assertEquals(
            expected = Pascal(6 / 3),
            actual = Newton(6) / SquareMeter(3))

    @Test
    fun jouleDivStere() =
        assertEquals(
            expected = Pascal(6 / 3),
            actual = Joule(6) / Stere(3))
}