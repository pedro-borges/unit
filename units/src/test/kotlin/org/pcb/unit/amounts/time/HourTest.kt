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
        Hour(1) equivalent Second(60 * 60)
        Hour(1) equivalent Minute(60)
        Hour(1) equivalent Hour(1)
        Hour(24) equivalent Day(1)

        Hour(1) equivalent TimeAmount(1, HOUR)
    }
}
