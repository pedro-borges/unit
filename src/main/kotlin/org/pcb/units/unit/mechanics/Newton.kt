package org.pcb.units.unit.mechanics

import org.pcb.units.domain.Force
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.Unit

class Newton : Unit<Force>(Force::class)
{
    override val symbol = "N"
    override val cannon = 1.0.toBigDecimal()

    companion object
    {
        val NEWTON = Newton()
    }
}