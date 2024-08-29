package org.pcb.units.unit.time

import org.pcb.units.Unit
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

object Day : Unit<Time>(Time::class)
{
    override val symbol = "day"
    override val cannon = 86400.toBigBigDecimal()
}