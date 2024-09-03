package org.pcb.unit.units.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Kilogram
import org.pcb.units.amount.mass.Pound

class PoundTest
{
    @Test
    fun scales()
    {
        Pound(1) equivalent Kilogram(0.45359237)

        Pound(1) equivalent Drachm(16 * 16)
        Pound(1) equivalent Ounce(16)
        Pound(1) equivalent Pound(1)
        Pound(14) equivalent Stone(1)
    }

    private infix fun Pound.equivalent(other: MassAmount<*>) =
        assertEquals(this, Pound(0) + other)
}