package org.pcb.units.unit.metric

import java.math.BigDecimal.ONE
import org.pcb.units.domain.Time
import org.pcb.units.unit.Unit

class Second : Unit<Time>(Time::class)
{
    override val symbol = "s"
    override val cannon = ONE

    companion object
    {
        val SECONDS = Second()
    }
}