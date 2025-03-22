package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.Inch.INCH
import org.pcb.unit.base.Meter

class InchTest
{
    private infix fun Inch.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Inch(1) equivalent DistanceAmount(1, INCH)

        assertEquals(Inch(1), Thou(1000).convertTo(INCH))
        assertEquals(Inch(1), Inch(1).convertTo(INCH))
        assertEquals(Inch(4), Hand(1).convertTo(INCH))
        assertEquals(Inch(4 * 3), Foot(1).convertTo(INCH))
        assertEquals(Inch(4 * 3 * 3), Yard(1).convertTo(INCH))
        assertEquals(Inch(4 * 3 * 3 * 22), Chain(1).convertTo(INCH))
        assertEquals(Inch(4 * 3 * 3 * 22 * 10), Furlong(1).convertTo(INCH))
        assertEquals(Inch(4 * 3 * 3 * 22 * 10 * 8), Mile(1).convertTo(INCH))
        assertEquals(Inch(4 * 3 * 3 * 22 * 10 * 8 * 3), League(1).convertTo(INCH))
        assertEquals(Inch(1), Meter(0.0254).convertTo(INCH))
    }
}
