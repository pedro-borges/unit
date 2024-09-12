package org.pcb.unit.amounts.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.base.Second
import org.pcb.unit.amounts.time.Day.DAY
import org.pcb.unit.units.TimeAmount

class DayTest
{
    private infix fun Day.equivalent(other: TimeAmount<*>) =
        assertEquals(this, Day(0) + other)

    @Test
    fun scales()
    {
        Day(1) equivalent Second(60 * 60 * 24)
        Day(1) equivalent Minute(60 * 24)
        Day(1) equivalent Hour(24)
        Day(1) equivalent Day(1)

        Day(1) equivalent TimeAmount(1, DAY)
    }
}
