package org.pcb.units.unit.time

import org.pcb.units.Unit
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

object Hour : Unit<Time>(Time::class)
{
    override val symbol = "h"
    override val cannon = 3600.toBigBigDecimal()
}