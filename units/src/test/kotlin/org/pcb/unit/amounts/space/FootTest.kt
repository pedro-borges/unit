package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.Foot.FOOT
import org.pcb.unit.base.Meter

class FootTest
{
    private infix fun Foot.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Foot(1) equivalent DistanceAmount(1, FOOT)

        assertEquals(Foot(1), Thou(3 * 4 * 1000).convertTo(FOOT))
        assertEquals(Foot(1), Inch(3 * 4).convertTo(FOOT))
        assertEquals(Foot(1), Hand(3).convertTo(FOOT))
        assertEquals(Foot(1), Foot(1).convertTo(FOOT))
        assertEquals(Foot(3), Yard(1).convertTo(FOOT))
        assertEquals(Foot(3 * 22), Chain(1).convertTo(FOOT))
        assertEquals(Foot(3 * 22 * 10), Furlong(1).convertTo(FOOT))
        assertEquals(Foot(3 * 22 * 10 * 8), Mile(1).convertTo(FOOT))
        assertEquals(Foot(3 * 22 * 10 * 8 * 3), League(1).convertTo(FOOT))
        assertEquals(Foot(1), Meter(0.3048).convertTo(FOOT))
    }
}
