package org.pcb.units.unit.time

import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.Unit

class Hour : Unit<Time>(Time::class)
{
    override val symbol = "h"
    override val cannon = 3600.0.toBigDecimal()

    companion object
    {
        val HOUR = Hour()
    }
}