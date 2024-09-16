package org.pcb.unit.amounts.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.amounts.base.Gram
import org.pcb.unit.amounts.base.Gram.GRAM

class GramTest
{
    private infix fun Gram.equivalent(other: MassAmount<*>) =
        assertEquals(this, Gram(0) + other)

    @Test
    fun scales()
    {
        Gram(1) equivalent Gram(0.001, KILO)

        Gram(1) equivalent MassAmount(1, GRAM)
    }
}