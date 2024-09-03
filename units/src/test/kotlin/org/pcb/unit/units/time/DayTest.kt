package org.pcb.unit.units.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.base.Second

class DayTest
{
    @Test
    fun scales()
    {
        Day(1) equivalent Second(60 * 60 * 24)
        Day(1) equivalent Minute(60 * 24)
        Day(1) equivalent Hour(24)
        Day(1) equivalent Day(1)
    }

    private infix fun Day.equivalent(other: TimeAmount<*>) =
        assertEquals(this, Day(0) + other)
}
