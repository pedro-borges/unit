package org.pcb.units.amount.mechanics

import org.pcb.units.Unit
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.mass.Gram.GRAM
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Second.SECOND
import org.pcb.units.domain.Force
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Newton(amount: Number)
    : ForceAmount<GRAM, METER, SECOND>(amount.toBigBigDecimal(), GRAM, METER, SECOND)
{
    object NEWTON : Unit<Force>(Force::class)
    {
        override val symbol = "N"
        override val cannon = 0.001.toBigBigDecimal()
    }
}