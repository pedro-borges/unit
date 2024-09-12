package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.space.Hand.HAND
import org.pcb.unit.units.DistanceAmount

class HandTest
{
    private infix fun Hand.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, Hand(0) + other)

    @Test
    fun scales()
    {
        Hand(1) equivalent Meter(0.1016)

        Hand(1) equivalent Thou(4 * 1000)
        Hand(1) equivalent Inch(4)
        Hand(1) equivalent Hand(1)
        Hand(3) equivalent Foot(1)
        Hand(3 * 3) equivalent Yard(1)
        Hand(3 * 3 * 22) equivalent Chain(1)
        Hand(3 * 3 * 22 * 10) equivalent Furlong(1)
        Hand(3 * 3 * 22 * 10 * 8) equivalent Mile(1)
        Hand(3 * 3 * 22 * 10 * 8 * 3) equivalent League(1)

        Hand(1) equivalent DistanceAmount(1, HAND)
    }
}
