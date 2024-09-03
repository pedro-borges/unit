package org.pcb.unit.units.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.units.amount.mass.Pound

class OunceTest
{
    @Test
    fun scales()
    {
        Ounce(1) equivalent Kilogram(0.028349523125)

        Ounce(1) equivalent Drachm(16)
        Ounce(16) equivalent Pound(1)
        Ounce(14 * 16) equivalent Stone(1)
    }

    private infix fun Ounce.equivalent(other: MassAmount<*>) =
        assertEquals(this, Ounce(0) + other)
}