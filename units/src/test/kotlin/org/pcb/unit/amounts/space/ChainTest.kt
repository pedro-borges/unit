package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.base.Meter
import org.pcb.unit.amounts.space.Chain.CHAIN
import org.pcb.unit.units.DistanceAmount

class ChainTest
{
    private infix fun Chain.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, Chain(0) + other)

    @Test
    fun scales()
    {
        Chain(1) equivalent Meter(20.1168)

        Chain(1) equivalent Thou(22 * 3 * 3 * 4 * 1000)
        Chain(1) equivalent Inch(22 * 3 * 3 * 4)
        Chain(1) equivalent Hand(22 * 3 * 3)
        Chain(1) equivalent Foot(22 * 3)
        Chain(1) equivalent Yard(22)
        Chain(1) equivalent Chain(1)
        Chain(10) equivalent Furlong(1)
        Chain(10 * 8) equivalent Mile(1)
        Chain(10 * 8 * 3) equivalent League(1)

        Chain(1) equivalent DistanceAmount(1, CHAIN)
    }
}
