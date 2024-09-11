package org.pcb.unit.units.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.DistanceAmount
import org.pcb.unit.units.base.Meter
import org.pcb.unit.units.space.Thou.THOU

class ThouTest
{
    private infix fun Thou.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, Thou(0) + other)

    @Test
    fun scales()
    {
        Thou(1) equivalent Meter(0.0000254)

        Thou(1) equivalent Thou(1)
        Thou(1000) equivalent Inch(1)
        Thou(1000 * 4) equivalent Hand(1)
        Thou(1000 * 4 * 3) equivalent Foot(1)
        Thou(1000 * 4 * 3 * 3) equivalent Yard(1)
        Thou(1000 * 4 * 3 * 3 * 22) equivalent Chain(1)
        Thou(1000 * 4 * 3 * 3 * 22 * 10) equivalent Furlong(1)
        Thou(1000 * 4 * 3 * 3 * 22 * 10 * 8) equivalent Mile(1)
        Thou(1000 * 4 * 3 * 3 * 22 * 10 * 8 * 3) equivalent League(1)

        Thou(1) equivalent DistanceAmount(1, THOU)
    }
}
