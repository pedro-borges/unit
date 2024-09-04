package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.base.Meter

class FootTest
{
    private infix fun Foot.equivalent(other: LengthAmount<*>) =
        assertEquals(this, Foot(0) + other)

    @Test
    fun scales()
    {
        Foot(1) equivalent Meter(0.3048)

        Foot(1) equivalent Thou(3 * 4 * 1000)
        Foot(1) equivalent Inch(3 * 4)
        Foot(1) equivalent Hand(3)
        Foot(1) equivalent Foot(1)
        Foot(3) equivalent Yard(1)
        Foot(3 * 22) equivalent Chain(1)
        Foot(3 * 22 * 10) equivalent Furlong(1)
        Foot(3 * 22 * 10 * 8) equivalent Mile(1)
        Foot(3 * 22 * 10 * 8 * 3) equivalent League(1)
    }
}
