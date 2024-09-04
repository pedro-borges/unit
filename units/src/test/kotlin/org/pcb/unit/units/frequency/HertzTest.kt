package org.pcb.unit.units.frequency

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.div
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.frequency.Hertz.HERTZ

class HertzTest
{
    private infix fun Hertz.equivalent(other: FrequencyAmount<*>) =
        assertEquals(this, Hertz(0) + other)

    @Test
    fun unit()
    {
        Hertz(2) equivalent 2 / Second(1)
        Hertz(2) equivalent FrequencyAmount(2, HERTZ)
    }

    @Test
    fun scalarDivTime() =
        assertEquals(
            expected = Hertz(6 / 3),
            actual = 6 / Second(3))
}