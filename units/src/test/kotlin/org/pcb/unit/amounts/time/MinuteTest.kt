package org.pcb.unit.amounts.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.amounts.TimeAmount
import org.pcb.unit.amounts.time.Minute.MINUTE
import org.pcb.unit.base.Second

class MinuteTest
{
    private infix fun Minute.equivalent(other: TimeAmount<*>) =
        assertEquals(this, other)

    @Test
    fun scales()
    {
        Minute(1) equivalent TimeAmount(1, MINUTE)

        assertEquals(Minute(1), Second(60).convertTo(MINUTE))
        assertEquals(Minute(1), Minute(1).convertTo(MINUTE))
        assertEquals(Minute(60), Hour(1).convertTo(MINUTE))
        assertEquals(Minute(60 * 24), Day(1).convertTo(MINUTE))
    }
}
