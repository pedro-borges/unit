package org.pcb.unit.units.time

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.TimeAmount
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.electromagnetism.Farad
import org.pcb.unit.units.electromagnetism.Ohm

class SecondTest
{
    @Test
    fun scales()
    {
        Second(1) equivalent Second(1)
        Second(60) equivalent Minute(1)
        Second(60 * 60) equivalent Hour(1)
        Second(60 * 60 * 24) equivalent Day(1)

        assertEquals(
            expected = Second(amount = 2 * 3),
            actual = Ohm(2) * Farad(3))

        assertEquals(
            expected = Second(amount = 2 * 3),
            actual = Farad(2) * Ohm(3))
    }

    private infix fun Second.equivalent(other: TimeAmount<*>) =
        assertEquals(this, Second(0) + other)
}
