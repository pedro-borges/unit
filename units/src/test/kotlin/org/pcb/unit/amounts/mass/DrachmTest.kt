package org.pcb.unit.amounts.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.mass.Drachm.DRACHM

class DrachmTest
{
    private infix fun Drachm.equivalent(other: MassAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Drachm(1) equivalent Gram(0.0017718451953125, KILO)

        Drachm(1) equivalent Drachm(1)
        Drachm(16) equivalent Ounce(1)
        Drachm(16 * 16) equivalent Pound(1)
        Drachm(16 * 16 * 14) equivalent Stone(1)

        Drachm(1) equivalent MassAmount(1, DRACHM)
    }
}