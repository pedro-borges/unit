package org.pcb.unit.amounts.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.amounts.mass.Stone.STONE
import org.pcb.unit.base.Gram

class StoneTest
{
    private infix fun Stone.equivalent(other: MassAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Stone(1) equivalent MassAmount(1, STONE)

        assertEquals(Stone(1), Drachm(14 * 16 * 16).convertTo(STONE))
        assertEquals(Stone(1), Ounce(14 * 16).convertTo(STONE))
        assertEquals(Stone(1), Pound(14).convertTo(STONE))
        assertEquals(Stone(1), Stone(1).convertTo(STONE))
        assertEquals(Stone(1), Gram(6.35029318, KILO).convertTo(STONE))
    }
}