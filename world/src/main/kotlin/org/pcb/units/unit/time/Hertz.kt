package org.pcb.units.unit.time

import org.pcb.units.Unit
import org.pcb.units.domain.Frequency
import org.pcb.units.toBigBigDecimal

object Hertz : Unit<Frequency>(Frequency::class)
{
    override val symbol = "Hz"
    override val cannon = 1.toBigBigDecimal()
}