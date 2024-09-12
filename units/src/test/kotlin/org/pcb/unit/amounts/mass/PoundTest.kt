package org.pcb.unit.amounts.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.mass.Pound.POUND

class PoundTest
{
    private infix fun Pound.equivalent(other: MassAmount<*>) =
        assertEquals(this, Pound(0) + other)

    @Test
    fun scales()
    {
        Pound(1) equivalent Gram(0.45359237, KILO)

        Pound(1) equivalent Drachm(16 * 16)
        Pound(1) equivalent Ounce(16)
        Pound(1) equivalent Pound(1)
        Pound(14) equivalent Stone(1)

        Pound(1) equivalent MassAmount(1, POUND)
    }
}