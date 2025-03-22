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
        League(1) equivalent DistanceAmount(1, LEAGUE)

        assertEquals(League(1), Thou(3 * 8 * 10 * 22 * 3 * 3 * 4 * 1000).convertTo(LEAGUE))
        assertEquals(League(1), Inch(3 * 8 * 10 * 22 * 3 * 3 * 4).convertTo(LEAGUE))
        assertEquals(League(1), Hand(3 * 8 * 10 * 22 * 3 * 3).convertTo(LEAGUE))
        assertEquals(League(1), Foot(3 * 8 * 10 * 22 * 3).convertTo(LEAGUE))
        assertEquals(League(1), Yard(3 * 8 * 10 * 22).convertTo(LEAGUE))
        assertEquals(League(1), Chain(3 * 8 * 10).convertTo(LEAGUE))
        assertEquals(League(1), Furlong(3 * 8).convertTo(LEAGUE))
        assertEquals(League(1), Mile(3).convertTo(LEAGUE))
        assertEquals(League(1), League(1).convertTo(LEAGUE))
        assertEquals(League(1), Meter(4828.032).convertTo(LEAGUE))
    }
}
