package org.pcb.unit.amounts.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.mass.Ounce.OUNCE

class OunceTest
{
    private infix fun Ounce.equivalent(other: MassAmount<*>) =
        assertEquals(this, Ounce(0) + other)

    @Test
    fun scales()
    {
        Ounce(1) equivalent Gram(0.028349523125, KILO)

        Ounce(1) equivalent Drachm(16)
        Ounce(1) equivalent Ounce(1)
        Ounce(16) equivalent Pound(1)
        Ounce(16 * 14) equivalent Stone(1)

        Ounce(1) equivalent MassAmount(1, OUNCE)
    }
}