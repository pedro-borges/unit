package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.Chain.CHAIN
import org.pcb.unit.base.Meter

class ChainTest
{
    private infix fun Chain.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Chain(1) equivalent DistanceAmount(1, CHAIN)

        assertEquals(Chain(1), Thou(22 * 3 * 3 * 4 * 1000).convertTo(CHAIN))
        assertEquals(Chain(1), Inch(22 * 3 * 3 * 4).convertTo(CHAIN))
        assertEquals(Chain(1), Hand(22 * 3 * 3).convertTo(CHAIN))
        assertEquals(Chain(1), Foot(22 * 3).convertTo(CHAIN))
        assertEquals(Chain(1), Yard(22).convertTo(CHAIN))
        assertEquals(Chain(1), Chain(1).convertTo(CHAIN))
        assertEquals(Chain(10), Furlong(1).convertTo(CHAIN))
        assertEquals(Chain(10 * 8), Mile(1).convertTo(CHAIN))
        assertEquals(Chain(10 * 8 * 3), League(1).convertTo(CHAIN))
        assertEquals(Chain(1), Meter(20.1168).convertTo(CHAIN))
    }
}
