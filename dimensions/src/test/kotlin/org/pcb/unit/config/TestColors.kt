package org.pcb.unit.config

import kotlin.test.Test
import org.pcb.unit.config.Color.NONE

class TestColors
{
    @Test
    fun testColors()
    {
        Color.entries.forEach {
            println(it.iso + it.name + NONE.iso)
        }
    }
}