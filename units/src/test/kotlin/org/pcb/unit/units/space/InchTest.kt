package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.base.Meter

class InchTest
{
    @Test
    fun scales()
    {
        Inch(1) equivalent Meter(0.0254)

        Inch(1) equivalent Thou(1000)
        Inch(1) equivalent Inch(1)
        Inch(4) equivalent Hand(1)
        Inch(4 * 3) equivalent Foot(1)
        Inch(4 * 3 * 3) equivalent Yard(1)
        Inch(4 * 3 * 3 * 22) equivalent Chain(1)
        Inch(4 * 3 * 3 * 22 * 10) equivalent Furlong(1)
        Inch(4 * 3 * 3 * 22 * 10 * 8) equivalent Mile(1)
        Inch(4 * 3 * 3 * 22 * 10 * 8 * 3) equivalent League(1)
    }

    private infix fun Inch.equivalent(other: LengthAmount<*>) =
        assertEquals(this, Inch(0) + other)
}
