package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.space.Furlong.FURLONG
import org.pcb.unit.units.DistanceAmount

class FurlongTest
{
    private infix fun Furlong.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, Furlong(0) + other)

    @Test
    fun scales()
    {
        Furlong(1) equivalent Meter(201.168)

        Furlong(1) equivalent Thou(10 * 22 * 3 * 3 * 4 * 1000)
        Furlong(1) equivalent Inch(10 * 22 * 3 * 3 * 4)
        Furlong(1) equivalent Hand(10 * 22 * 3 * 3)
        Furlong(1) equivalent Foot(10 * 22 * 3)
        Furlong(1) equivalent Yard(10 * 22)
        Furlong(1) equivalent Chain(10)
        Furlong(1) equivalent Furlong(1)
        Furlong(8) equivalent Mile(1)
        Furlong(8 * 3) equivalent League(1)

        Furlong(1) equivalent DistanceAmount(1, FURLONG)
    }
}
