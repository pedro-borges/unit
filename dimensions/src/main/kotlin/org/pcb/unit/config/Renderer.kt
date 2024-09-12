package org.pcb.unit.config

import org.pcb.unit.Amount
import org.pcb.unit.Unit

interface Renderer
{
    fun render(amount: Amount<*>): String

    fun render(units: Map<Unit<*>, Int>): String

    fun render(unit: Unit<*>): String
}