package org.pcb.units.unit.imperial

import org.pcb.units.Unit
import org.pcb.units.domain.Space
import org.pcb.units.toBigBigDecimal

object Foot : Unit<Space>(Space::class)
{
    override val symbol = "ft"
    override val cannon = 0.3048.toBigBigDecimal()
}