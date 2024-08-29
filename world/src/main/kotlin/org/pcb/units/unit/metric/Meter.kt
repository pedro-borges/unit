package org.pcb.units.unit.metric

import org.pcb.units.Unit
import org.pcb.units.domain.Space
import org.pcb.units.toBigBigDecimal

object Meter : Unit<Space>(Space::class)
{
    override val symbol = "m"
    override val cannon = 1.toBigBigDecimal()
}