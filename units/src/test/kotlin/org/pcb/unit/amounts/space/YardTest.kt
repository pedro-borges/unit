package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.Yard.YARD
import org.pcb.unit.base.Meter

class YardTest
{
    private infix fun Yard.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Yard(1) equivalent DistanceAmount(1, YARD)

        assertEquals(Yard(1), Thou(3 * 3 * 4 * 1000).convertTo(YARD))
        assertEquals(Yard(1), Inch(3 * 3 * 4).convertTo(YARD))
        assertEquals(Yard(1), Hand(3 * 3).convertTo(YARD))
        assertEquals(Yard(1), Foot(3).convertTo(YARD))
        assertEquals(Yard(1), Yard(1).convertTo(YARD))
        assertEquals(Yard(22), Chain(1).convertTo(YARD))
        assertEquals(Yard(22 * 10), Furlong(1).convertTo(YARD))
        assertEquals(Yard(22 * 10 * 8), Mile(1).convertTo(YARD))
        assertEquals(Yard(22 * 10 * 8 * 3), League(1).convertTo(YARD))
        assertEquals(Yard(1), Meter(0.9144).convertTo(YARD))
    }
}
