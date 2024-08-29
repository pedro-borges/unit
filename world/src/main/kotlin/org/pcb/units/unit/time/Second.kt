package org.pcb.units.unit.time

import org.pcb.units.Unit
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

object Second : Unit<Time>(Time::class)
{
    override val symbol = "s"
    override val cannon = 1.toBigBigDecimal()
}