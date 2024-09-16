package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.space.Inch.INCH

class InchTest
{
    private infix fun Inch.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

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

        Inch(1) equivalent DistanceAmount(1, INCH)
    }
}
