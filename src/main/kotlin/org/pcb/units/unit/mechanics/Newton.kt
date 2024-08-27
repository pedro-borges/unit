package org.pcb.units.unit.mechanics

import java.math.BigDecimal.ONE
import org.pcb.units.domain.Force
import org.pcb.units.unit.Unit

class Newton : Unit<Force>(Force::class)
{
    override val symbol = "N"
    override val cannon = ONE

    companion object
    {
        val NEWTONS = Newton()
    }
}