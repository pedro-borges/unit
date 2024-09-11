package org.pcb.unit.units.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Gram
import org.pcb.units.amount.mass.Pound

class StoneTest
{
    private infix fun Stone.equivalent(other: MassAmount<*>) =
        assertEquals(this, Stone(0) + other)

    @Test
    fun scales()
    {
        Stone(1) equivalent Gram(6.35029318, KILO)

        Stone(1) equivalent Drachm(14 * 16 * 16)
        Stone(1) equivalent Ounce(14 * 16)
        Stone(1) equivalent Pound(14)
        Stone(1) equivalent Stone(1)
    }
}