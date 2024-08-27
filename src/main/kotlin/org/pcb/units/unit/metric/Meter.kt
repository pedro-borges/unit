package org.pcb.units.unit.metric

import java.math.BigDecimal.ONE
import org.pcb.units.domain.Space
import org.pcb.units.unit.Unit

class Meter : Unit<Space>(Space::class)
{
    override val symbol = "m"
    override val cannon = ONE

    companion object
    {
        val METERS = Meter()
    }
}