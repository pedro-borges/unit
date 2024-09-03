package org.pcb.unit.units.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.units.amount.mass.Pound

class DrachmTest
{
    @Test
    fun scales()
    {
        Drachm(1) equivalent Kilogram(0.0017718451953125)

        Drachm(1) equivalent Drachm(1)
        Drachm(16) equivalent Ounce(1)
        Drachm(16 * 16) equivalent Pound(1)
        Drachm(16 * 16 * 14) equivalent Stone(1)
    }

    private infix fun Drachm.equivalent(other: MassAmount<*>) =
        assertEquals(this, Drachm(0) + other)
}