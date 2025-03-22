package org.pcb.unit.amounts.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.amounts.mass.Pound.POUND
import org.pcb.unit.base.Gram

class PoundTest
{
    private infix fun Pound.equivalent(other: MassAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Pound(1) equivalent MassAmount(1, POUND)

        assertEquals(Pound(1), Drachm(16 * 16).convertTo(POUND))
        assertEquals(Pound(1), Ounce(16).convertTo(POUND))
        assertEquals(Pound(1), Pound(1).convertTo(POUND))
        assertEquals(Pound(14), Stone(1).convertTo(POUND))
        assertEquals(Pound(1), Gram(0.45359237, KILO).convertTo(POUND))
    }
}