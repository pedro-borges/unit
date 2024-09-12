package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.space.Mile.MILE

class MileTest
{
    private infix fun Mile.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, Mile(0) + other)

    @Test
    fun scales()
    {
        Mile(1) equivalent Meter(1609.344)

        Mile(1) equivalent Thou(8 * 10 * 22 * 3 * 3 * 4 * 1000)
        Mile(1) equivalent Inch(8 * 10 * 22 * 3 * 3 * 4)
        Mile(1) equivalent Hand(8 * 10 * 22 * 3 * 3)
        Mile(1) equivalent Foot(8 * 10 * 22 * 3)
        Mile(1) equivalent Yard(8 * 10 * 22)
        Mile(1) equivalent Chain(8 * 10)
        Mile(1) equivalent Furlong(8)
        Mile(1) equivalent Mile(1)
        Mile(3) equivalent League(1)

        Mile(1) equivalent DistanceAmount(1, MILE)
    }
}
