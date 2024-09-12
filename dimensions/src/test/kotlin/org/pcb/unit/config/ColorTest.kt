package org.pcb.unit.config

import kotlin.test.Test
import org.pcb.unit.config.Color.NONE

class ColorTest
{
    @Test
    fun testColors()
    {
        println(Color.entries
            .map { it.iso + it.name + NONE.iso }
            .joinToString(" "))
    }
}