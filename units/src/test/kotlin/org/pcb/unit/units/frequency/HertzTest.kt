package org.pcb.unit.units.frequency

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.base.Second
import org.pcb.unit.units.scalar.NaturalAmount

class HertzTest
{
    @Test
    fun unit()
    {
        Hertz(1) equivalent NaturalAmount(1) / Second(1)
    }

    private infix fun Hertz.equivalent(other: FrequencyAmount<*>) =
        assertEquals(this, Hertz(0) + other)
}