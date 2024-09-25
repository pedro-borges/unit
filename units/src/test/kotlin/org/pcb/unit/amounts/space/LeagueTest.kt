package org.pcb.unit.amounts.space

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.space.League.LEAGUE
import org.pcb.unit.base.Meter

class LeagueTest
{
    private infix fun League.equivalent(other: DistanceAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        League(1) equivalent Meter(4828.032)

        League(1) equivalent Thou(3 * 8 * 10 * 22 * 3 * 3 * 4 * 1000)
        League(1) equivalent Inch(3 * 8 * 10 * 22 * 3 * 3 * 4)
        League(1) equivalent Hand(3 * 8 * 10 * 22 * 3 * 3)
        League(1) equivalent Foot(3 * 8 * 10 * 22 * 3)
        League(1) equivalent Yard(3 * 8 * 10 * 22)
        League(1) equivalent Chain(3 * 8 * 10)
        League(1) equivalent Furlong(3 * 8)
        League(1) equivalent Mile(3)
        League(1) equivalent League(1)

        League(1) equivalent DistanceAmount(1, LEAGUE)
    }
}
