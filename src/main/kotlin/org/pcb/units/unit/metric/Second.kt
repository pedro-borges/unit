package org.pcb.units.unit.metric

import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.Unit

class Second : Unit<Time>(Time::class)
{
    override val symbol = "s"
    override val cannon = 1.0.toBigDecimal()

    companion object
    {
        val SECOND = Second()
    }
}