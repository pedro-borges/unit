package org.pcb.unit.units.mass

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Gram

class GramTest
{
    private infix fun Gram.equivalent(other: MassAmount<*>) =
        assertEquals(this, Gram(0) + other)

    @Test
    fun scales()
    {
        Gram(1) equivalent Gram(0.001, KILO)
    }
}