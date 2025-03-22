package org.pcb.unit.amounts.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.TimeAmount
import org.pcb.unit.amounts.time.Day.DAY
import org.pcb.unit.base.Second

class DayTest
{
    private infix fun Day.equivalent(other: TimeAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Day(1) equivalent TimeAmount(1, DAY)

        assertEquals(Day(1), Day(1).convertTo(DAY))
        assertEquals(Day(1), Hour(24).convertTo(DAY))
        assertEquals(Day(1), Minute(60 * 24).convertTo(DAY))
        assertEquals(Day(1), Second(60 * 60 * 24).convertTo(DAY))
    }
}
