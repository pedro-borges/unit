package org.pcb.unit

import kotlin.test.Test
import kotlin.test.assertEquals

class EarthTest
{
    @Test
    fun airIsComplete()
    {
        assertEquals(1.0, Earth.air.map { it.value }.reduce(Double::plus), 0.0001)
    }
}