package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.Mile.MILE
import org.pcb.unit.base.Meter

class MileTest
{
    private infix fun Mile.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Mile(1) equivalent DistanceAmount(1, MILE)

        assertEquals(Mile(1), Thou(8 * 10 * 22 * 3 * 3 * 4 * 1000).convertTo(MILE))
        assertEquals(Mile(1), Inch(8 * 10 * 22 * 3 * 3 * 4).convertTo(MILE))
        assertEquals(Mile(1), Hand(8 * 10 * 22 * 3 * 3).convertTo(MILE))
        assertEquals(Mile(1), Foot(8 * 10 * 22 * 3).convertTo(MILE))
        assertEquals(Mile(1), Yard(8 * 10 * 22).convertTo(MILE))
        assertEquals(Mile(1), Chain(8 * 10).convertTo(MILE))
        assertEquals(Mile(1), Furlong(8).convertTo(MILE))
        assertEquals(Mile(1), Mile(1).convertTo(MILE))
        assertEquals(Mile(3), League(1).convertTo(MILE))
        assertEquals(Mile(1), Meter(1609.344).convertTo(MILE))
    }
}
