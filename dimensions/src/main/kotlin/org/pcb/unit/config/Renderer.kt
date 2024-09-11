package org.pcb.unit.config

import org.pcb.unit.Unit

interface Renderer
{
    fun render(units: Map<Unit<*>, Int>): String
}