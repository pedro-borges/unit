package org.pcb.unit.amounts.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.amounts.mass.Ounce.OUNCE
import org.pcb.unit.base.Gram

class OunceTest
{
    private infix fun Ounce.equivalent(other: MassAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Ounce(1) equivalent MassAmount(1, OUNCE)

        assertEquals(Ounce(1), Drachm(16).convertTo(OUNCE))
        assertEquals(Ounce(1), Ounce(1).convertTo(OUNCE))
        assertEquals(Ounce(16), Pound(1).convertTo(OUNCE))
        assertEquals(Ounce(16 * 14), Stone(1).convertTo(OUNCE))
        assertEquals(Ounce(1), Gram(0.028349523125, KILO).convertTo(OUNCE))
    }
}