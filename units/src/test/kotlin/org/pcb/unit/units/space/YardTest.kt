package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.base.Meter

class YardTest
{
    @Test
    fun scales()
    {
        Yard(1) equivalent Meter(0.9144)

        Yard(1) equivalent Thou(3 * 3 * 4 * 1000)
        Yard(1) equivalent Inch(3 * 3 * 4)
        Yard(1) equivalent Hand(3 * 3)
        Yard(1) equivalent Foot(3)
        Yard(1) equivalent Yard(1)
        Yard(22) equivalent Chain(1)
        Yard(22 * 10) equivalent Furlong(1)
        Yard(22 * 10 * 8) equivalent Mile(1)
        Yard(22 * 10 * 8 * 3) equivalent League(1)
    }

    private infix fun Yard.equivalent(other: LengthAmount<*>) =
        assertEquals(this, Yard(0) + other)
}
