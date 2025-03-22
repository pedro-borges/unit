package org.pcb.unit.amounts.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.TimeAmount
import org.pcb.unit.amounts.time.Hour.HOUR
import org.pcb.unit.base.Second

class HourTest
{
    private infix fun Hour.equivalent(other: TimeAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Hour(1) equivalent TimeAmount(1, HOUR)

        assertEquals(Hour(24), Day(1).convertTo(HOUR))
        assertEquals(Hour(1), Hour(1).convertTo(HOUR))
        assertEquals(Hour(1), Minute(60).convertTo(HOUR))
        assertEquals(Hour(1), Second(60 * 60).convertTo(HOUR))
    }
}
