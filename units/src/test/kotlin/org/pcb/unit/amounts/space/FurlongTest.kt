package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.Furlong.FURLONG
import org.pcb.unit.base.Meter

class FurlongTest
{
    private infix fun Furlong.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Furlong(1) equivalent DistanceAmount(1, FURLONG)

        assertEquals(Furlong(1), Thou(10 * 22 * 3 * 3 * 4 * 1000).convertTo(FURLONG))
        assertEquals(Furlong(1), Inch(10 * 22 * 3 * 3 * 4).convertTo(FURLONG))
        assertEquals(Furlong(1), Hand(10 * 22 * 3 * 3).convertTo(FURLONG))
        assertEquals(Furlong(1), Foot(10 * 22 * 3).convertTo(FURLONG))
        assertEquals(Furlong(1), Yard(10 * 22).convertTo(FURLONG))
        assertEquals(Furlong(1), Chain(10).convertTo(FURLONG))
        assertEquals(Furlong(1), Furlong(1).convertTo(FURLONG))
        assertEquals(Furlong(8), Mile(1).convertTo(FURLONG))
        assertEquals(Furlong(8 * 3), League(1).convertTo(FURLONG))
        assertEquals(Furlong(1), Meter(201.168).convertTo(FURLONG))
    }
}
