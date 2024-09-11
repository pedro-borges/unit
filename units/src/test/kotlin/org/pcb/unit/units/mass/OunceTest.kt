package org.pcb.unit.units.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Gram
import org.pcb.unit.units.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound

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