package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.Thou.THOU
import org.pcb.unit.base.Meter

class ThouTest
{
    private infix fun Thou.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Thou(1) equivalent DistanceAmount(1, THOU)

        assertEquals(Thou(1), Thou(1).convertTo(THOU))
        assertEquals(Thou(1000), Inch(1).convertTo(THOU))
        assertEquals(Thou(1000 * 4), Hand(1).convertTo(THOU))
        assertEquals(Thou(1000 * 4 * 3), Foot(1).convertTo(THOU))
        assertEquals(Thou(1000 * 4 * 3 * 3), Yard(1).convertTo(THOU))
        assertEquals(Thou(1000 * 4 * 3 * 3 * 22), Chain(1).convertTo(THOU))
        assertEquals(Thou(1000 * 4 * 3 * 3 * 22 * 10), Furlong(1).convertTo(THOU))
        assertEquals(Thou(1000 * 4 * 3 * 3 * 22 * 10 * 8), Mile(1).convertTo(THOU))
        assertEquals(Thou(1000 * 4 * 3 * 3 * 22 * 10 * 8 * 3), League(1).convertTo(THOU))
        assertEquals(Thou(1), Meter(0.0000254).convertTo(THOU))
    }
}
