package org.pcb.units.unit.time

import org.pcb.units.Unit
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

object Minute : Unit<Time>(Time::class)
{
    override val symbol = "min"
    override val cannon = 60.toBigBigDecimal()
}