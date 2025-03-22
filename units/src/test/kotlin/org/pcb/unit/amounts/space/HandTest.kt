package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.Hand.HAND
import org.pcb.unit.base.Meter

class HandTest
{
    private infix fun Hand.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Hand(1) equivalent DistanceAmount(1, HAND)

        assertEquals(Hand(1), Thou(4 * 1000).convertTo(HAND))
        assertEquals(Hand(1), Inch(4).convertTo(HAND))
        assertEquals(Hand(1), Hand(1).convertTo(HAND))
        assertEquals(Hand(3), Foot(1).convertTo(HAND))
        assertEquals(Hand(3 * 3), Yard(1).convertTo(HAND))
        assertEquals(Hand(3 * 3 * 22), Chain(1).convertTo(HAND))
        assertEquals(Hand(3 * 3 * 22 * 10), Furlong(1).convertTo(HAND))
        assertEquals(Hand(3 * 3 * 22 * 10 * 8), Mile(1).convertTo(HAND))
        assertEquals(Hand(3 * 3 * 22 * 10 * 8 * 3), League(1).convertTo(HAND))
        assertEquals(Hand(1), Meter(0.1016).convertTo(HAND))
    }
}
