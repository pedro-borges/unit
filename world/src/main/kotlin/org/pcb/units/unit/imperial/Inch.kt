package org.pcb.units.unit.imperial

import org.pcb.units.Unit
import org.pcb.units.domain.Space
import org.pcb.units.toBigBigDecimal

object Inch : Unit<Space>(Space::class)
{
    override val symbol = "in"
    override val cannon = 0.0254.toBigBigDecimal()
}