package org.pcb.units.unit.mechanics

import org.pcb.units.Unit
import org.pcb.units.domain.Force
import org.pcb.units.toBigBigDecimal

object Newton : Unit<Force>(Force::class)
{
    override val symbol = "N"
    override val cannon = 1.toBigBigDecimal()
}