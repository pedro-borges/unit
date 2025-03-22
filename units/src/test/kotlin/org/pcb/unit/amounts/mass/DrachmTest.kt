package org.pcb.unit.amounts.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.amounts.mass.Drachm.DRACHM
import org.pcb.unit.amounts.mass.Ounce.OUNCE
import org.pcb.unit.amounts.mass.Pound.POUND
import org.pcb.unit.amounts.mass.Stone.STONE
import org.pcb.unit.base.Gram
import org.pcb.unit.base.Gram.GRAM

class DrachmTest
{
    private infix fun Drachm.equivalent(other: MassAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Drachm(1) equivalent MassAmount(1, DRACHM)
        assertEquals(Drachm(1), Drachm(1).convertTo(DRACHM))
        assertEquals(Ounce(1), Drachm(16).convertTo(OUNCE))
        assertEquals(Pound(1), Drachm(16 * 16).convertTo(POUND))
        assertEquals(Stone(1), Drachm(16 * 16 * 14).convertTo(STONE))
        assertEquals(Gram(0.0017718451953125, KILO), Drachm(1).convertTo(GRAM))
    }
}