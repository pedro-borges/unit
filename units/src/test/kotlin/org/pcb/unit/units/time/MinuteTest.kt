package org.pcb.unit.units.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.base.Second

class MinuteTest
{
    private infix fun Minute.equivalent(other: TimeAmount<*>) =
        assertEquals(this, Minute(0) + other)

    @Test
    fun scales()
    {
        Minute(1) equivalent Second(60)
        Minute(1) equivalent Minute(1)
        Minute(60) equivalent Hour(1)
        Minute(60 * 24) equivalent Day(1)
    }
}
